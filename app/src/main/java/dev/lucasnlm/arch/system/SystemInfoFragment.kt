package dev.lucasnlm.arch.system

import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.core.BaseFragment
import dev.lucasnlm.arch.system.presenter.SystemInfoPresenter
import dev.lucasnlm.arch.system.view.SystemInfoView

open class SystemInfoFragment: BaseFragment<SystemInfoView, SystemInfoPresenter>() {
    override val fragmentLayout: Int = R.layout.fragment_system_info
}
