package dev.lucasnlm.arch.phone

import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.core.BaseFragment
import dev.lucasnlm.arch.phone.presenter.PhoneInfoPresenter
import dev.lucasnlm.arch.phone.view.PhoneInfoView

class PhoneInfoFragment: BaseFragment<PhoneInfoView, PhoneInfoPresenter>() {
    override val layoutRes: Int = R.layout.fragment_device_info
}
