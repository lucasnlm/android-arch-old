package dev.lucasnlm.arch.system.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.cpu.MockSystemInfo
import dev.lucasnlm.arch.system.data.SystemInfoLoader
import dev.lucasnlm.arch.system.model.SystemInfo
import io.reactivex.Single

@Module
open class SystemModule {

    @Provides
    fun provideSystemInfoLoader(): SystemInfoLoader = MockSystemInfoLoader()

    internal class MockSystemInfoLoader: SystemInfoLoader() {

        override fun load(): Single<SystemInfo> = Single.just(MockSystemInfo.systemInfo1)
    }
}