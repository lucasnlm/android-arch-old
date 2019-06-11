package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.cpu.MockCpuInfo
import dev.lucasnlm.arch.soc.data.CpuInfoLoader
import dev.lucasnlm.arch.cpu.RawPropCpuInfo

@Module
open class DeviceModule {

    @Provides
    fun provideInternalDataReader(): InternalDataReader = object : InternalDataReader() {
        override fun read(source: String): String =
            when(source) {
                CpuInfoLoader.CPU_GOVERNOR -> MockCpuInfo.mockGovernor
                CpuInfoLoader.CPU_INFO_COMMAND -> RawPropCpuInfo.propCpuInfo1
                CpuInfoLoader.makeClockCommand(0) -> "1000000"
                CpuInfoLoader.makeClockCommand(1) -> "1500000"
                CpuInfoLoader.makeClockCommand(2) -> "500000"
                CpuInfoLoader.makeClockCommand(3) -> "0"
                else -> super.read(source)
            }
    }

    @Provides
    open fun provideDeviceInfo(): DeviceInfo = object : DeviceInfo {

        override fun getCpuCoresNumber(): Int = MockCpuInfo.mockCoreCount

        override fun getPlatformAbi(): String = MockCpuInfo.mockAbi
    }
}
