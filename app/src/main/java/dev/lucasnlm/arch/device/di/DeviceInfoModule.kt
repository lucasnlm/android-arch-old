package dev.lucasnlm.arch.device.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.device.presenter.DeviceInfoPresenter
import dev.lucasnlm.arch.device.view.DeviceInfoView

@Module
class DeviceInfoModule {

    @Provides
    fun provideDeviceInfoView(): DeviceInfoView = DeviceInfoView()

    @Provides
    fun provideDeviceInfoPresenter(): DeviceInfoPresenter = DeviceInfoPresenter()
}
