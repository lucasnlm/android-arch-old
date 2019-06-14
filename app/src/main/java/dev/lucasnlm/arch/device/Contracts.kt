package dev.lucasnlm.arch.device

import dev.lucasnlm.arch.core.view.BaseView
import dev.lucasnlm.arch.device.model.DisplayInfo
import io.reactivex.Single

interface Contracts {

    interface View: BaseView {
        fun showInfo(displayInfo: DisplayInfo)
    }

    interface Presenter {
        fun loadDeviceInfo()
    }

    interface Interactor {
        fun getDeviceInfo(): Single<DisplayInfo>
    }
}
