package dev.lucasnlm.arch.soc.data

import com.nhaarman.mockitokotlin2.*
import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.cpu.MockCpuInfo
import dev.lucasnlm.arch.soc.data.CpuInfoLoader.Companion.CPU_INFO_COMMAND
import dev.lucasnlm.arch.soc.data.CpuInfoLoader.Companion.CPU_GOVERNOR
import dev.lucasnlm.arch.soc.data.CpuInfoLoader.Companion.makeClockCommand
import dev.lucasnlm.arch.soc.data.CpuInfoLoader.Companion.makeMaxClockCommand
import dev.lucasnlm.arch.soc.data.CpuInfoLoader.Companion.makeMinClockCommand
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import java.util.concurrent.TimeUnit

class CpuInfoLoaderTest {

    private val scheduler = TestScheduler()

    @Test
    fun testGetCpuInfo1() {
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn MockCpuInfo.mockCpuInfo1.first
            on { read(CPU_GOVERNOR)} doReturn MockCpuInfo.mockGovernor
            on { read(makeClockCommand(0)) } doReturn "1000000"
            on { read(makeClockCommand(1)) } doReturn "1500000"
            on { read(makeClockCommand(2)) } doReturn "500000"
            on { read(makeClockCommand(3)) } doReturn "0"
            repeat(4) {
                on { read(makeMaxClockCommand(it)) } doReturn "1500000"
                on { read(makeMinClockCommand(it)) } doReturn "500000"
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
            on { read(CPU_INFO_COMMAND) } doReturn MockCpuInfo.mockCpuInfo2.first
            on { read(CPU_GOVERNOR)} doReturn MockCpuInfo.mockGovernor
            repeat(4) {
                on { read(makeMaxClockCommand(it)) } doReturn "3798000"
                on { read(makeMinClockCommand(it)) } doReturn "3798000"
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
            on { read(CPU_INFO_COMMAND) } doReturn MockCpuInfo.mockCpuInfo3.first
            on { read(CPU_GOVERNOR)} doReturn MockCpuInfo.mockGovernor
            repeat(4) {
                on { read(makeMaxClockCommand(it)) } doReturn "2195000"
                on { read(makeMinClockCommand(it)) } doReturn "2195000"
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
        val numberOfCores = 4
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn MockCpuInfo.mockCpuInfo1.first
            on { read(CPU_GOVERNOR)} doReturn MockCpuInfo.mockGovernor
            repeat(numberOfCores) {
                on { read(makeClockCommand(it)) } doReturn "1000"
                on { read(makeMinClockCommand(it)) } doReturn "1000"
                on { read(makeMaxClockCommand(it)) } doReturn "1000"
            }
        }

        val deviceInfo: DeviceInfo = mock {
            on { getCpuCoresNumber() } doReturn MockCpuInfo.mockCoreCount
            on { getPlatformAbi() } doReturn MockCpuInfo.mockAbi
        }

        val observer = CpuInfoLoader(scheduler, internalDataReader, deviceInfo).listenClockInfo().test()

        val seconds = 8
        scheduler.advanceTimeBy(seconds.toLong(), TimeUnit.SECONDS)
        scheduler.triggerActions()

        observer.run {
            assertNoErrors()
            assertNotTerminated()
            assertNotComplete()
        }

        repeat(numberOfCores) {
            verify(internalDataReader, times(seconds)).read(makeClockCommand(it))
            verify(internalDataReader, times(seconds)).read(makeMinClockCommand(it))
            verify(internalDataReader, times(seconds)).read(makeMaxClockCommand(it))
        }
    }
}
