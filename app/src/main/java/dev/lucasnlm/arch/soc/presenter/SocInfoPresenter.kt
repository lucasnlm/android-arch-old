package dev.lucasnlm.arch.soc.presenter

import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.soc.Contracts
import dev.lucasnlm.arch.soc.model.CpuClockInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.model.GpuInfo
import dev.lucasnlm.arch.soc.interactor.SocInfoInteractor
import dev.lucasnlm.arch.soc.view.SocInfoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

class SocInfoPresenter @Inject constructor(
    private val socInfoInteractor: SocInfoInteractor
) : BasePresenter<SocInfoView>(), Contracts.Presenter {

    override fun onCreate() {
        super.onCreate()

        view?.let { socInfoView ->
            Observables.combineLatest(
                    socInfoInteractor.getCpuInfo().toObservable(),
                    socInfoInteractor.listenClockInfo(),
                    socInfoView.gpuInfoObservable
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .distinctUntilChanged()
                .subscribe(
                    { onGetInfo(it.first, it.second, it.third) },
                    { onError(it) }).addToDisposables()
        } ?: onError(Exception("Null view"))
    }

    override fun onGetInfo(cpuInfo: CpuInfo, clockInfo: CpuClockInfo, gpuInfo: GpuInfo) {
        view?.hideProgress()

        onCpuInfoLoaded(cpuInfo)
        onCpuClocksLoaded(clockInfo)
        onGpuInfoLoaded(gpuInfo)
    }

    override fun onError(tr: Throwable) {
        // TODO implement / handle error UI
    }

    private fun onCpuInfoLoaded(cpuInfo: CpuInfo) {
        view?.run {
            showInfo(cpuInfo)
            showFlags(cpuInfo.flags)
        }

        onCpuClocksLoaded(cpuInfo.clockInfo)
    }

    private fun onCpuClocksLoaded(clockInfo: CpuClockInfo) {
        clockInfo.run {
            view?.showClocks(maxClock, minClock, clocks)
        }
    }

    private fun onGpuInfoLoaded(gpuInfo: GpuInfo) {
        view?.showGpuInfo(gpuInfo)
    }
}
