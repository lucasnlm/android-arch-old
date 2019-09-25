package dev.lucasnlm.arch.core

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.core.view.BaseActivityView
import javax.inject.Inject

abstract class BaseActivity<U : BaseActivityView, T : BasePresenter<U>> :
    DaggerAppCompatActivity() {

    @Inject
    lateinit var mvpPresenter: T

    @Inject
    lateinit var mvpView: U

    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        mvpView.run {
            setActivity(this@BaseActivity)
            onViewCreated(window.decorView)
        }

        mvpPresenter.run {
            onAttach(mvpView)
            onCreate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mvpPresenter.run {
            onDetach()
            onDestroy()
        }

        mvpView.freeActivity()
    }
}
