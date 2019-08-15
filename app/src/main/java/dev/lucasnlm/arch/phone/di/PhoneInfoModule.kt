package dev.lucasnlm.arch.phone.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.phone.presenter.PhoneInfoPresenter
import dev.lucasnlm.arch.phone.view.PhoneInfoView

@Module
class PhoneInfoModule {

    @Provides
    fun providePhoneInfoView(): PhoneInfoView = PhoneInfoView()

    @Provides
    fun providePhoneInfoPresenter(): PhoneInfoPresenter = PhoneInfoPresenter()
}
