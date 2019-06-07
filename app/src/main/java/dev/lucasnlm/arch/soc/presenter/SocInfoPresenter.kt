package dev.lucasnlm.arch.soc.presenter

import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.soc.Contracts
import dev.lucasnlm.arch.soc.model.CpuClockInfo
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.model.GpuInfo
import dev.lucasnlm.arch.soc.data.CpuInfoLoader
import dev.lucasnlm.arch.soc.interactor.SocInfoInteractor
import dev.lucasnlm.arch.soc.view.SocInfoView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.microedition.khronos.opengles.GL10

class SocInfoPresenter @Inject constructor(
    private val socInfoInteractor: SocInfoInteractor
) : BasePresenter<SocInfoView>(), Contracts.Presenter {

    override fun onCreate() {
        super.onCreate()
        loadCpuInfo()
        loadGpuInfo()
    }

    override fun loadCpuInfo() {
        socInfoInteractor
            .getCpuInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCpuInfoLoaded, ::onFailToLoadCpuInfo)
            .addToDisposables()

        socInfoInteractor
            .listenClockInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCpuClocksLoaded, ::onFailToLoadCpuInfo)
            .addToDisposables()
    }

    override fun loadGpuInfo() {
        view?.gpuInfoObservable
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.map {
                GpuInfo(
                    it[GL10.GL_RENDERER].orEmpty(),
                    it[GL10.GL_VENDOR].orEmpty(),
                    it[GL10.GL_VERSION].orEmpty(),
                    it[GL10.GL_EXTENSIONS].orEmpty().split(" ").filter { ext -> ext.isNotEmpty() }
                )
            }
            ?.subscribe(::onGpuInfoLoaded, ::onFailToLoadGpuInfo)
            ?.addToDisposables()
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

    private fun onFailToLoadCpuInfo(throwable: Throwable) {
        // TODO handle error
        throwable.printStackTrace()
    }

    private fun onFailToLoadGpuInfo(throwable: Throwable) {
        // TODO handle error
        throwable.printStackTrace()
    }
}
