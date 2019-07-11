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
    fun provideSystemInfoPresenter(systemInfoInteractor: SystemInfoInteractor): SystemInfoPresenter =
        SystemInfoPresenter(systemInfoInteractor)

    @Provides
    fun provideSystemInfoInteractor(systemInfoRepository: Repository<SystemInfo>): SystemInfoInteractor =
        SystemInfoInteractor(systemInfoRepository)

    @Provides
    fun provideSystemInfoRepository(systemInfoLoader: SystemInfoLoader): Repository<SystemInfo> =
        SystemInfoRepository(systemInfoLoader)
}
