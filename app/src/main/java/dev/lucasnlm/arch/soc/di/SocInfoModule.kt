package dev.lucasnlm.arch.soc.di

import dagger.Module
import dagger.Provides
import dev.lucasnlm.arch.common.InternalDataReader
import dev.lucasnlm.arch.core.repository.Repository
import dev.lucasnlm.arch.soc.NativeHardwareInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.presenter.SocInfoPresenter
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader
import dev.lucasnlm.arch.soc.repository.CpuInfoRepository
import dev.lucasnlm.arch.soc.view.SocInfoView
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class SocInfoModule {

    @Provides
    @Singleton
    fun provideCpuInfoLoader(): CpuInfoLoader = CpuInfoLoader(Schedulers.io(), provideInternalDataReader(), provideNativeBridge())

    @Provides
    fun provideCpuInfoRepository(): Repository<CpuInfo> = CpuInfoRepository(provideCpuInfoLoader())

    @Singleton
    @Provides
    fun provideCpuInfoPresenter(cpuInfoLoader: CpuInfoLoader): SocInfoPresenter = SocInfoPresenter(cpuInfoLoader)

    @Singleton
    @Provides
    fun provideCpuInfoView(): SocInfoView = SocInfoView()

    @Singleton
    @Provides
    fun provideInternalDataReader(): InternalDataReader = InternalDataReader()

    @Singleton
    @Provides
    fun provideNativeBridge(): NativeHardwareInfo = NativeHardwareInfo()
}
