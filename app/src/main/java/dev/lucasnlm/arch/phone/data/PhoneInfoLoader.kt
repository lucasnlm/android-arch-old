package dev.lucasnlm.arch.phone.data

import android.app.Application
import android.content.Context
import dev.lucasnlm.arch.phone.model.DisplayInfo
import dev.lucasnlm.arch.phone.model.PhoneInfo
import io.reactivex.Single
import javax.inject.Inject
import android.graphics.Point
import android.view.WindowManager

class PhoneInfoLoader @Inject constructor(
    private val application: Application
) {

    fun getPhoneInfo(): Single<PhoneInfo> = Single.fromCallable {
        val windowManager = application.getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        val displayInfo = DisplayInfo(size.x, size.y, 0.0)

        PhoneInfo(displayInfo)
    }
}
