package dev.lucasnlm.arch.system.presenter

import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.system.Contract
import dev.lucasnlm.arch.system.view.SystemInfoView

class SystemInfoPresenter: BasePresenter<SystemInfoView>(), Contract.Presenter {

    override fun onCreate() {
        super.onCreate()
        loadSystemInfo()
    }

    override fun loadSystemInfo() {

    }
}
