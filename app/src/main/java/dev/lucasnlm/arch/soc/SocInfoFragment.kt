package dev.lucasnlm.arch.soc

import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.core.BaseFragment
import dev.lucasnlm.arch.soc.presenter.SocInfoPresenter
import dev.lucasnlm.arch.soc.view.SocInfoView

open class SocInfoFragment: BaseFragment<SocInfoView, SocInfoPresenter>() {
    override val fragmentLayout: Int = R.layout.fragment_soc_info
}
