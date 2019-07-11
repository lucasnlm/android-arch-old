package dev.lucasnlm.arch.system

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.cpu.MockSystemInfo
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

        val systemInfo = MockSystemInfo.systemInfo1
        isInfoListDisplayed(R.id.details_list, mapOf(
            "Android" to  systemInfo.androidName!!,
            "API" to systemInfo.androidApi.toString()
        ))

        val productInfo = systemInfo.productInfo
        isInfoListDisplayed(R.id.product_list, mapOf(
            "Model" to productInfo.model,
            "Product" to productInfo.product,
            "Device" to productInfo.deviceName,
            "Bootloader" to productInfo.bootloader,
            "Board" to productInfo.boardName
        ))

        val brand = systemInfo.brand
        isInfoListDisplayed(R.id.brand_list, mapOf(
            "Brand" to brand.brand,
            "Manufacturer" to brand.manufacturer
        ))

        val version = systemInfo.version
        isInfoListDisplayed(R.id.version_list, mapOf(
            "Codename" to version.codename,
            "Release" to version.release,
            "Security Patch" to version.securityPatch!!
        ))
    }
}
