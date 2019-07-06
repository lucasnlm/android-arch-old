package dev.lucasnlm.arch.main.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.main.interactor.MainActivityInteractor
import dev.lucasnlm.arch.main.presenter.MainActivityPresenter
import dev.lucasnlm.arch.main.view.MainActivityView

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityView(): MainActivityView = MainActivityView()

    @Provides
    fun provideMainActivityPresenter(mainActivityInteractor: MainActivityInteractor): MainActivityPresenter =
        MainActivityPresenter(mainActivityInteractor)

    @Provides
    fun provideMainActivityInteractor(): MainActivityInteractor = MainActivityInteractor()
}
