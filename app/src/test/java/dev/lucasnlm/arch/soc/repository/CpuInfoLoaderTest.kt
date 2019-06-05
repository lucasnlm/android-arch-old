package dev.lucasnlm.arch.soc.repository

import com.nhaarman.mockitokotlin2.*
import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.cpu.MockCpuInfo
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader.Companion.CPU_INFO_COMMAND
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader.Companion.CPU_GOVERNOR
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader.Companion.makeClockCommand
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader.Companion.makeMaxClockCommand
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader.Companion.makeMinClockCommand
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import java.io.ByteArrayInputStream
import java.util.concurrent.TimeUnit

class CpuInfoLoaderTest {

    private val scheduler = TestScheduler()

    @Test
    fun testGetCpuInfo1() {
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn ByteArrayInputStream(MockCpuInfo.mockCpuInfo1.first.toByteArray())
            on { read(CPU_GOVERNOR)} doReturn ByteArrayInputStream(MockCpuInfo.mockGovernor.toByteArray())
            on { read(makeClockCommand(0)) } doReturn ByteArrayInputStream("1000000".toByteArray())
            on { read(makeClockCommand(1)) } doReturn ByteArrayInputStream("1500000".toByteArray())
            on { read(makeClockCommand(2)) } doReturn ByteArrayInputStream("500000".toByteArray())
            on { read(makeClockCommand(3)) } doReturn ByteArrayInputStream("0".toByteArray())
            repeat(4) {
                on { read(makeMaxClockCommand(it)) } doReturn ByteArrayInputStream("1500000".toByteArray())
                on { read(makeMinClockCommand(it)) } doReturn ByteArrayInputStream("500000".toByteArray())
            }
        }

        val deviceInfo: DeviceInfo = mock {
            on { getCpuCoresNumber() } doReturn MockCpuInfo.mockCoreCount
            on { getPlatformAbi() } doReturn MockCpuInfo.mockAbi
        }

        CpuInfoLoader(scheduler, internalDataReader, deviceInfo).getCpuInfo().test().assertNoErrors().assertValue(
            MockCpuInfo.mockCpuInfo1.second
        )
    }

    @Test
    fun testGetCpuInfo2() {
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn ByteArrayInputStream(MockCpuInfo.mockCpuInfo2.first.toByteArray())
            on { read(CPU_GOVERNOR)} doReturn ByteArrayInputStream(MockCpuInfo.mockGovernor.toByteArray())
            repeat(4) {
                on { read(makeMaxClockCommand(it)) } doReturn ByteArrayInputStream("3798000".toByteArray())
                on { read(makeMinClockCommand(it)) } doReturn ByteArrayInputStream("3798000".toByteArray())
            }
        }

        val deviceInfo: DeviceInfo = mock {
            on { getCpuCoresNumber() } doReturn MockCpuInfo.mockCoreCount
            on { getPlatformAbi() } doReturn MockCpuInfo.mockAbi
        }

        CpuInfoLoader(scheduler, internalDataReader, deviceInfo).getCpuInfo().test().assertNoErrors().assertValue(
            MockCpuInfo.mockCpuInfo2.second
        )
    }

    @Test
    fun testGetCpuInfo3() {
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn ByteArrayInputStream(MockCpuInfo.mockCpuInfo3.first.toByteArray())
            on { read(CPU_GOVERNOR)} doReturn ByteArrayInputStream(MockCpuInfo.mockGovernor.toByteArray())
            repeat(4) {
                on { read(makeMaxClockCommand(it)) } doReturn ByteArrayInputStream("2195000".toByteArray())
                on { read(makeMinClockCommand(it)) } doReturn ByteArrayInputStream("2195000".toByteArray())
            }
        }

        val deviceInfo: DeviceInfo = mock {
            on { getCpuCoresNumber() } doReturn MockCpuInfo.mockCoreCount
            on { getPlatformAbi() } doReturn MockCpuInfo.mockAbi
        }

        CpuInfoLoader(scheduler, internalDataReader, deviceInfo).getCpuInfo().test().assertNoErrors().assertValue(
            MockCpuInfo.mockCpuInfo3.second
        )
    }

    @Test
    fun testListenClocks() {
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn ByteArrayInputStream(MockCpuInfo.mockCpuInfo1.first.toByteArray())
            on { read(CPU_GOVERNOR)} doReturn ByteArrayInputStream(MockCpuInfo.mockGovernor.toByteArray())
            repeat(4) {
                on { read(makeClockCommand(it)) } doReturn ByteArrayInputStream("1000".toByteArray())
                on { read(makeMinClockCommand(it)) } doReturn ByteArrayInputStream("1000".toByteArray())
                on { read(makeMaxClockCommand(it)) } doReturn ByteArrayInputStream("1000".toByteArray())
            }
        }

        val deviceInfo: DeviceInfo = mock {
            on { getCpuCoresNumber() } doReturn MockCpuInfo.mockCoreCount
            on { getPlatformAbi() } doReturn MockCpuInfo.mockAbi
        }

        val observer = CpuInfoLoader(scheduler, internalDataReader, deviceInfo).listenClockInfo().test()

        scheduler.advanceTimeBy(4, TimeUnit.SECONDS)
        scheduler.triggerActions()

        observer.run {
            assertNoErrors()
            assertNotTerminated()
            assertNotComplete()
        }

        repeat(4) {
            verify(internalDataReader).read(makeClockCommand(it))
            verify(internalDataReader, times(4)).read(makeMinClockCommand(it))
            verify(internalDataReader, times(4)).read(makeMaxClockCommand(it))
        }
    }
}
