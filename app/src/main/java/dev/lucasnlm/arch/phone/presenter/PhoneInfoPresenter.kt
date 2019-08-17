package dev.lucasnlm.arch.phone.presenter

import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.phone.Contracts
import dev.lucasnlm.arch.phone.interactor.PhoneInfoInteractor
import dev.lucasnlm.arch.phone.model.PhoneInfo
import dev.lucasnlm.arch.phone.view.PhoneInfoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhoneInfoPresenter @Inject constructor(
    private val phoneInfoInteractor: PhoneInfoInteractor
) : BasePresenter<PhoneInfoView>(), Contracts.Presenter {

    override fun onCreate() {
        super.onCreate()
        loadPhoneInfo()
    }

    override fun loadPhoneInfo() {
        phoneInfoInteractor.loadPhoneInfo().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe(::onPhoneInfoLoader, ::onLoadFailed).addToDisposables()
    }

    private fun onPhoneInfoLoader(phoneInfo: PhoneInfo) {
        view?.showInfo(phoneInfo)
    }

    private fun onLoadFailed(throwable: Throwable) {
        // TODO
        throwable.printStackTrace()
    }
}
