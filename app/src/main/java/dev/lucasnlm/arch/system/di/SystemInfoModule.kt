package dev.lucasnlm.arch.system.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.system.presenter.SystemInfoPresenter
import dev.lucasnlm.arch.system.view.SystemInfoView

@Module
open class SystemInfoModule {

    @Provides
    fun provideSystemInfoPresenter(): SystemInfoPresenter = SystemInfoPresenter()

    @Provides
    fun provideSystemInfoView(): SystemInfoView = SystemInfoView()

}