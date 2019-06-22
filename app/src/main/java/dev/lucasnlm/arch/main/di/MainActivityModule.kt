package dev.lucasnlm.arch.main.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.main.presenter.MainActivityPresenter
import dev.lucasnlm.arch.main.view.MainActivityView

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityView(): MainActivityView = MainActivityView()

    @Provides
    fun provideMainActivityPresenter(): MainActivityPresenter = MainActivityPresenter()
}
