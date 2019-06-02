package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.core.system.NativeDeviceInfo

@Module
open class DeviceModule {

    @Provides
    fun provideInternalDataReader(): InternalDataReader = InternalDataReader()

    @Provides
    open fun provideDeviceInfo(): DeviceInfo = NativeDeviceInfo()
}
