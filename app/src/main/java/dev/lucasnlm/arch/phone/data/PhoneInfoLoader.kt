package dev.lucasnlm.arch.phone.data

import android.app.Application
import dev.lucasnlm.arch.phone.model.PhoneInfo
import dev.lucasnlm.arch.phone.repository.DisplayInfoProvider
import io.reactivex.Single
import javax.inject.Inject

class PhoneInfoLoader @Inject constructor(
    private val application: Application,
    private val displayInfoProvider: DisplayInfoProvider = DisplayInfoProvider(application)
) {
    fun getPhoneInfo(): Single<PhoneInfo> = Single.fromCallable {
        val displayInfo = displayInfoProvider.provideDisplayMetrics()
        PhoneInfo(displayInfo)
    }
}
