package dev.lucasnlm.arch.phone.data

import android.app.Application
import dev.lucasnlm.arch.phone.model.DisplayInfo
import dev.lucasnlm.arch.phone.model.PhoneInfo
import io.reactivex.Single
import javax.inject.Inject

class PhoneInfoLoader @Inject constructor(
    private val application: Application
) {

    fun getPhoneInfo(): Single<PhoneInfo> = Single.fromCallable {
        val displayInfo = DisplayInfo(0, 0, 0.0)

        PhoneInfo(displayInfo)
    }
}
