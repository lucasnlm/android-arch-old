package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.cpu.MockCpuInfo
import dev.lucasnlm.arch.soc.data.CpuInfoLoader
import dev.lucasnlm.arch.cpu.RawPropCpuInfo
import java.io.ByteArrayInputStream
import java.io.InputStream

@Module
open class DeviceModule {

    @Provides
    fun provideInternalDataReader(): InternalDataReader = object : InternalDataReader() {
        override fun read(source: String): InputStream =
            when(source) {
                CpuInfoLoader.CPU_GOVERNOR -> ByteArrayInputStream(MockCpuInfo.mockGovernor.toByteArray())
                CpuInfoLoader.CPU_INFO_COMMAND -> ByteArrayInputStream(RawPropCpuInfo.propCpuInfo1.toByteArray())
                CpuInfoLoader.makeClockCommand(0) -> ByteArrayInputStream("1000000".toByteArray())
                CpuInfoLoader.makeClockCommand(1) -> ByteArrayInputStream("1500000".toByteArray())
                CpuInfoLoader.makeClockCommand(2) -> ByteArrayInputStream("500000".toByteArray())
                CpuInfoLoader.makeClockCommand(3) -> ByteArrayInputStream("0".toByteArray())
                else -> super.read(source)
            }
    }

    @Provides
    open fun provideDeviceInfo(): DeviceInfo = object : DeviceInfo {

        override fun getCpuCoresNumber(): Int = MockCpuInfo.mockCoreCount

        override fun getPlatformAbi(): String = MockCpuInfo.mockAbi
    }
}
