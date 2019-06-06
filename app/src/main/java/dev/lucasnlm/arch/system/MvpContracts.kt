package dev.lucasnlm.arch.system

import dev.lucasnlm.arch.core.view.BaseView
import dev.lucasnlm.arch.system.model.SystemInfo

interface Contract {

    interface View: BaseView {
        fun showInfo(cpuInfo: SystemInfo)
    }

    interface Presenter {
        fun loadSystemInfo()
    }
}
