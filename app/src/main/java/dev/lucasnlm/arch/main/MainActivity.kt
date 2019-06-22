package dev.lucasnlm.arch.main

import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.core.BaseActivity
import dev.lucasnlm.arch.main.presenter.MainActivityPresenter
import dev.lucasnlm.arch.main.view.MainActivityView

class MainActivity : BaseActivity<MainActivityView, MainActivityPresenter>() {
    override val layoutRes: Int = R.layout.activity_main
}
