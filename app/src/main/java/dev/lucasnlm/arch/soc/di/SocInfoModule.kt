package dev.lucasnlm.arch.soc.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.core.system.InternalDataReader
import dev.lucasnlm.arch.core.repository.Repository
import dev.lucasnlm.arch.core.system.DeviceInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.presenter.SocInfoPresenter
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader
import dev.lucasnlm.arch.soc.repository.CpuInfoRepository
import dev.lucasnlm.arch.soc.view.SocInfoView
import io.reactivex.schedulers.Schedulers

@Module
open class SocInfoModule {

    @Provides
    fun provideCpuInfoPresenter(cpuInfoLoader: CpuInfoLoader): SocInfoPresenter = SocInfoPresenter(cpuInfoLoader)

    @Provides
    fun provideCpuInfoView(): SocInfoView = SocInfoView()

    @Provides
    fun provideCpuInfoLoader(internalDataReader: InternalDataReader, deviceInfo: DeviceInfo): CpuInfoLoader =
        CpuInfoLoader(Schedulers.io(), internalDataReader, deviceInfo)

    @Provides
    fun provideCpuInfoRepository(internalDataReader: InternalDataReader, deviceInfo: DeviceInfo): Repository<CpuInfo> =
        CpuInfoRepository(provideCpuInfoLoader(internalDataReader, deviceInfo))
}
