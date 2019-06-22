package dev.lucasnlm.arch.core

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.core.view.BaseActivityView
import javax.inject.Inject

abstract class BaseActivity<U: BaseActivityView, T : BasePresenter<U>>: DaggerAppCompatActivity() {

    @Inject
    lateinit var mvpPresenter: T

    @Inject
    lateinit var mvpView: U

    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        mvpView.activity = this
        mvpView.onViewCreated(window.decorView)
        mvpPresenter.onAttach(mvpView)
        mvpPresenter.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        mvpPresenter.onDestroy()
        mvpView.activity = null
    }
}
