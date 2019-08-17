package dev.lucasnlm.arch.phone.interactor

import dev.lucasnlm.arch.phone.Contracts
import dev.lucasnlm.arch.phone.data.PhoneInfoLoader
import dev.lucasnlm.arch.phone.model.PhoneInfo
import io.reactivex.Single
import javax.inject.Inject

class PhoneInfoInteractor @Inject constructor(
    private val phoneInfoLoader: PhoneInfoLoader
) : Contracts.Interactor {

    override fun loadPhoneInfo(): Single<PhoneInfo> = phoneInfoLoader.getPhoneInfo()
}
