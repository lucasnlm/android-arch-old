package dev.lucasnlm.arch.soc.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.system.DataReader
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.soc.presenter.SocInfoPresenter
import dev.lucasnlm.arch.soc.data.CpuInfoLoader
import dev.lucasnlm.arch.soc.interactor.SocInfoInteractor
import dev.lucasnlm.arch.soc.view.SocInfoView
import io.reactivex.schedulers.Schedulers

@Module
open class SocInfoModule {

    @Provides
    fun provideCpuInfoView(): SocInfoView = SocInfoView()

    @Provides
    fun provideCpuInfoPresenter(socInfoInteractor: SocInfoInteractor): SocInfoPresenter =
        SocInfoPresenter(socInfoInteractor)

    @Provides
    fun provideSocInfoInteractor(dataReader: DataReader, deviceInfo: DeviceInfo): SocInfoInteractor =
        SocInfoInteractor(provideCpuInfoLoader(dataReader, deviceInfo))

    @Provides
    fun provideCpuInfoLoader(dataReader: DataReader, deviceInfo: DeviceInfo): CpuInfoLoader =
        CpuInfoLoader(Schedulers.io(), dataReader, deviceInfo)
}
