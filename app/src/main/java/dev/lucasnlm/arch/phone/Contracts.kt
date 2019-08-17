package dev.lucasnlm.arch.phone

import dev.lucasnlm.arch.core.view.BaseView
import dev.lucasnlm.arch.phone.model.PhoneInfo
import io.reactivex.Single

interface Contracts {

    interface View: BaseView {
        fun showInfo(phoneInfo: PhoneInfo)
    }

    interface Presenter {
        fun loadPhoneInfo()
    }

    interface Interactor {
        fun loadPhoneInfo(): Single<PhoneInfo>
    }
}
