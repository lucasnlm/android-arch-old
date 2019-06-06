package dev.lucasnlm.arch.system.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.repository.Repository
import dev.lucasnlm.arch.system.data.SystemInfoLoader
import dev.lucasnlm.arch.system.model.SystemInfo
import dev.lucasnlm.arch.system.presenter.SystemInfoPresenter
import dev.lucasnlm.arch.system.interactor.SystemInfoInteractor
import dev.lucasnlm.arch.system.repository.SystemInfoRepository
import dev.lucasnlm.arch.system.view.SystemInfoView

@Module
open class SystemInfoModule {

    @Provides
    fun provideSystemInfoView(): SystemInfoView = SystemInfoView()

    @Provides
    fun provideSystemInfoPresenter(): SystemInfoPresenter = SystemInfoPresenter(provideSystemInfoInterector())

    @Provides
    fun provideSystemInfoInterector(): SystemInfoInteractor = SystemInfoInteractor(provideSystemInfoRepository())

    @Provides
    fun provideCpuInfoLoader(): SystemInfoLoader = SystemInfoLoader()

    @Provides
    fun provideSystemInfoRepository(): Repository<SystemInfo> = SystemInfoRepository(provideCpuInfoLoader())
}
