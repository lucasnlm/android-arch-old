package dev.lucasnlm.arch.system.presenter

import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.system.Contracts
import dev.lucasnlm.arch.system.interactor.SystemInfoInteractor
import dev.lucasnlm.arch.system.model.SystemInfo
import dev.lucasnlm.arch.system.view.SystemInfoView
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class SystemInfoPresenter @Inject constructor(
    private val systemInfoInteractor: SystemInfoInteractor
): BasePresenter<SystemInfoView>(), Contracts.Presenter {

    override fun onCreate() {
        super.onCreate()
        loadSystemInfo()
    }

    override fun loadSystemInfo() {
        systemInfoInteractor.getSystemInfo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onSystemInfoLoaded, ::onLoadFailed)
            .addToDisposables()
    }

    private fun onSystemInfoLoaded(systemInfo: SystemInfo) {
        view?.showInfo(systemInfo)
    }

    private fun onLoadFailed(throwable: Throwable) {
        // TODO
        throwable.printStackTrace()
    }
}
