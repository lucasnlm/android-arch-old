package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.system.*

@Module
open class DeviceModule {

    @Provides
    fun provideDataReader(): DataReader = NativeDataReader()

    @Provides
    open fun provideDeviceInfo(): DeviceInfo = NativeDeviceInfo()
}
