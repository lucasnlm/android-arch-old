package dev.lucasnlm.arch.phone.repository

import android.app.Application
import dev.lucasnlm.arch.phone.model.DisplayInfo

class DisplayInfoProvider(
    private val application: Application
) {
    private fun getDpiLabel(density: Float): String =
        when {
            density >= 4.0 -> "XXXHDPI"
            density >= 3.0 -> "XXHDPI"
            density >= 2.0 -> "XHDPI"
            density >= 1.5 -> "HDPI"
            density >= 1.0 -> "MDPI"
            else -> "LDPI"
        }

    fun provideDisplayMetrics(): DisplayInfo {
        val metrics = application.resources.displayMetrics
        return DisplayInfo(metrics.widthPixels, metrics.heightPixels, metrics.densityDpi, getDpiLabel(metrics.density))
    }
}
