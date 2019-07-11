package dev.lucasnlm.arch.system

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.helper.isInfoListDisplayed
import dev.lucasnlm.arch.helper.waitFor
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SystemInfoFragmentTest {

    @Test
    fun testSystemInfoAreDisplayed() {
        launchFragmentInContainer<SystemInfoFragment>()

        waitFor(1000)

        isInfoListDisplayed(R.id.details_list, mapOf(
            "Android" to "Marshmallow",
            "API" to "23"
        ))

        isInfoListDisplayed(R.id.product_list, mapOf(
            "Model" to "XT1069",
            "Product" to "titan_retbr_dstv",
            "Device" to "titan_udstv",
            "Bootloader" to "0x4887",
            "Board" to "MSM8226"
        ))

        isInfoListDisplayed(R.id.brand_list, mapOf(
            "Brand" to "motorola",
            "Manufacturer" to "motorola"
        ))

        isInfoListDisplayed(R.id.version_list, mapOf(
            "Codename" to "REL",
            "Release" to "6.0",
            "Security Patch" to "2016-08-01"
        ))
    }
}
