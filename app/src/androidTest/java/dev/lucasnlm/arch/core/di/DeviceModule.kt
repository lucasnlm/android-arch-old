package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.system.DeviceInfo

@Module
open class DeviceModule {

    @Provides
    fun provideInternalDataReader(): InternalDataReader =
        InternalDataReader()

    @Provides
    open fun provideDeviceInfo(): DeviceInfo = object : DeviceInfo {

        override fun getCpuCoresNumber(): Int = 8

        override fun getPlatformAbi(): String = "testABI"
    }
}
