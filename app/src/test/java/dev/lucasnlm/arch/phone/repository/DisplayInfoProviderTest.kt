package dev.lucasnlm.arch.phone.repository

import android.app.Application
import android.content.res.Resources
import android.util.DisplayMetrics
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Test

class DisplayInfoProviderTest {

    private fun mockApplication(d: Float): Application {
        val resourcesMock: Resources = mock {
            on { displayMetrics } doReturn DisplayMetrics().apply {
                widthPixels = 800
                heightPixels = 600
                densityDpi = 200
                density = d
            }
        }

        return mock {
            on { resources } doReturn resourcesMock
        }
    }

    @Test
    fun testDisplayInfo() {
        val application = mockApplication(1.0f)

        val displayInfoProvider = DisplayInfoProvider(application)
        val displayInfo = displayInfoProvider.provideDisplayMetrics()
        assertEquals(800, displayInfo.width)
        assertEquals(600, displayInfo.height)
        assertEquals(200, displayInfo.dpi)
    }

    @Test
    fun testDisplayInfoLdpi() {
        assertDpiLabel(0.9f, "LDPI")
    }

    @Test
    fun testDisplayInfoMdpi() {
        assertDpiLabel(1.0f, "MDPI")
    }

    @Test
    fun testDisplayInfoMdpi2() {
        assertDpiLabel(1.25f, "MDPI")
    }

    @Test
    fun testDisplayInfoHdpi() {
        assertDpiLabel(1.6f, "HDPI")
    }

    @Test
    fun testDisplayInfoXhdpi() {
        assertDpiLabel(2.0f, "XHDPI")
    }

    @Test
    fun testDisplayInfoXxhdpi() {
        assertDpiLabel(3.0f, "XXHDPI")
    }

    @Test
    fun testDisplayInfoXxxhdpi() {
        assertDpiLabel(4.0f, "XXXHDPI")
    }

    private fun assertDpiLabel(density: Float, expectedLabel: String) {
        val application = mockApplication(density)
        val displayInfoProvider = DisplayInfoProvider(application)
        val displayInfo = displayInfoProvider.provideDisplayMetrics()
        assertEquals(expectedLabel, displayInfo.dpiLabel)
    }
}
