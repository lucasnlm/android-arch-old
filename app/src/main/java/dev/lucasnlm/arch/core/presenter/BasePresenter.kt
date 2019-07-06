package dev.lucasnlm.arch.core.presenter

import dev.lucasnlm.arch.core.view.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : BaseView> {

    protected var view: T? = null

    private val disposables = CompositeDisposable()

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    open fun onAttach(view: T) {
        this.view = view
    }

    open fun onDetach() {
        this.view = null
    }

    open fun onCreate() {}

    open fun onDestroy() {
        disposables.clear()
    }

    fun Disposable.addToDisposables() = addDisposable(this)
}
