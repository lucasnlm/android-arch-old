package dev.lucasnlm.arch.system

import dev.lucasnlm.arch.core.view.BaseView
import dev.lucasnlm.arch.system.model.SystemInfo
import io.reactivex.Single

interface Contracts {

    interface View: BaseView {
        fun showInfo(systemInfo: SystemInfo)
    }

    interface Presenter {
        fun loadSystemInfo()
    }

    interface Interactor {
        fun getSystemInfo(): Single<SystemInfo>
    }
}
