package dev.lucasnlm.arch.system.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.system.data.SystemInfoLoader

@Module
open class SystemModule {

    @Provides
    fun provideSystemInfoLoader(): SystemInfoLoader = SystemInfoLoader()
}