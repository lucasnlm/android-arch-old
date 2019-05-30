package dev.lucasnlm.arch.soc.repository

import com.nhaarman.mockitokotlin2.*
import dev.lucasnlm.arch.common.InternalDataReader
import dev.lucasnlm.arch.soc.HardwareInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader.Companion.CPU_INFO_COMMAND
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader.Companion.CPU_GOVERNOR
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader.Companion.makeClockCommand
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import java.io.ByteArrayInputStream
import java.util.concurrent.TimeUnit

class CpuInfoLoaderTest {

    private val scheduler = TestScheduler()

    @Test
    fun testGetCpuInfo1() {
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn ByteArrayInputStream(MockPropCpuInfo.propCpuInfo1.toByteArray())
            on { read(CPU_GOVERNOR)} doReturn ByteArrayInputStream("GOVERNOR".toByteArray())
            on { read(makeClockCommand(0)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(1)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(2)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(3)) } doReturn ByteArrayInputStream("1000".toByteArray())
        }

        val hardwareInfo: HardwareInfo = mock {
            on { getCpuCoresNumber() } doReturn 4
            on { getPlatformAbi() } doReturn "arm"
        }

        CpuInfoLoader(scheduler, internalDataReader, hardwareInfo).getCpuInfo().test().assertNoErrors().assertValue(
            CpuInfo(
                model = "AArch64 Processor rev 12 (aarch64)",
                modelName = "Qualcomm Technologies, Inc SDM845",
                vendor = "0x51",
                revision = "12",
                architecture = "8",
                cpuCores = 4,
                clocks = listOf(1, 1, 1 ,1),
                stepping = null,
                bogoMips = "38.40",
                bigLittle = 0,
                abi = "arm",
                serial = null,
                device = null,
                governor = "GOVERNOR",
                flags = "fp asimd evtstrm aes pmull sha1 sha2 crc32 atomics fphp asimdhp".split(" ")
            )
        )
    }

    @Test
    fun testGetCpuInfo2() {
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn ByteArrayInputStream(MockPropCpuInfo.propCpuInfo2.toByteArray())
            on { read(CPU_GOVERNOR)} doReturn ByteArrayInputStream("GOVERNOR".toByteArray())
            on { read(makeClockCommand(0)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(1)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(2)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(3)) } doReturn ByteArrayInputStream("1000".toByteArray())
        }

        val hardwareInfo: HardwareInfo = mock {
            on { getCpuCoresNumber() } doReturn 4
            on { getPlatformAbi() } doReturn "x86"
        }

        CpuInfoLoader(scheduler, internalDataReader, hardwareInfo).getCpuInfo().test().assertNoErrors().assertValue(
            CpuInfo(
                model = "1",
                modelName = "AMD Ryzen 7 1700 Eight-Core Processor",
                vendor = "AuthenticAMD",
                revision = null,
                architecture = null,
                cpuCores = 4,
                clocks = listOf(3798, 3798, 3798, 3798),
                stepping = "1",
                bogoMips = "7595.88",
                bigLittle = 0,
                abi = "x86",
                serial = null,
                device = null,
                governor = "GOVERNOR",
                flags = "fpu vme de pse tsc msr pae mce cx8 apic sep mtrr pge mca cmov pat pse36 clflush mmx fxsr sse".split(" ")
            )
        )
    }

    @Test
    fun testListenClocks() {
        val internalDataReader: InternalDataReader = mock {
            on { read(CPU_INFO_COMMAND) } doReturn ByteArrayInputStream(MockPropCpuInfo.propCpuInfo1.toByteArray())
            on { read(CPU_GOVERNOR)} doReturn ByteArrayInputStream("GOVERNOR".toByteArray())
            on { read(makeClockCommand(0)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(1)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(2)) } doReturn ByteArrayInputStream("1000".toByteArray())
            on { read(makeClockCommand(3)) } doReturn ByteArrayInputStream("1000".toByteArray())
        }

        val hardwareInfo: HardwareInfo = mock {
            on { getCpuCoresNumber() } doReturn 4
            on { getPlatformAbi() } doReturn "arm"
        }

        val observer = CpuInfoLoader(scheduler, internalDataReader, hardwareInfo).listenClocks().test()

        scheduler.advanceTimeBy(4, TimeUnit.SECONDS)
        scheduler.triggerActions()

        observer.run {
            assertNoErrors()
            assertNotTerminated()
            assertNotComplete()
        }

        verify(internalDataReader,  times(8)).read(any())
        verify(hardwareInfo,  times(4)).getCpuCoresNumber()
    }
}