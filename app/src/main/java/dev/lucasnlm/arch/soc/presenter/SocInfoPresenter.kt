package dev.lucasnlm.arch.soc.presenter

import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.soc.CpuInfoPresenterContract
import dev.lucasnlm.arch.soc.model.CpuInfo
import dev.lucasnlm.arch.soc.model.GpuInfo
import dev.lucasnlm.arch.soc.repository.CpuInfoLoader
import dev.lucasnlm.arch.soc.view.SocInfoView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.microedition.khronos.opengles.GL10

class SocInfoPresenter @Inject constructor(
    private val cpuInfoLoader: CpuInfoLoader
) : BasePresenter<SocInfoView>(), CpuInfoPresenterContract {

    override fun onCreate() {
        super.onCreate()
        loadCpuInfo()
        loadGpuInfo()
    }

    override fun loadCpuInfo() {
        cpuInfoLoader
            .getCpuInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onCpuInfoLoaded, ::onFailToLoadCpuInfo)
            .addToDisposables()

        cpuInfoLoader
            .listenClocks()
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
            showClocks(cpuInfo.clocks)
            showFlags(cpuInfo.flags)
        }
    }

    private fun onCpuClocksLoaded(clocks: List<Int>) {
        view?.showClocks(clocks)
    }

    private fun onFailToLoadCpuInfo(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private fun onGpuInfoLoaded(gpuInfo: GpuInfo) {
        view?.showGpuInfo(gpuInfo)
    }

    private fun onFailToLoadGpuInfo(throwable: Throwable) {
        throwable.printStackTrace()
    }
}
