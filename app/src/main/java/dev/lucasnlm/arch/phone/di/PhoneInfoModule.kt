package dev.lucasnlm.arch.phone.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.di.CoreModule
import dev.lucasnlm.arch.phone.data.PhoneInfoLoader
import dev.lucasnlm.arch.phone.interactor.PhoneInfoInteractor
import dev.lucasnlm.arch.phone.presenter.PhoneInfoPresenter
import dev.lucasnlm.arch.phone.view.PhoneInfoView

@Module(includes = [
    CoreModule::class
])
class PhoneInfoModule {

    @Provides
    fun providePhoneInfoView(): PhoneInfoView = PhoneInfoView()

    @Provides
    fun providePhoneInfoPresenter(phoneInfoInteractor: PhoneInfoInteractor): PhoneInfoPresenter = PhoneInfoPresenter(phoneInfoInteractor)

    @Provides
    fun provideDeviceInfoInteractor(phoneInfoLoader: PhoneInfoLoader): PhoneInfoInteractor = PhoneInfoInteractor(phoneInfoLoader)

    @Provides
    fun providePhoneInfoLoader(application: Application): PhoneInfoLoader = PhoneInfoLoader(application)
}
