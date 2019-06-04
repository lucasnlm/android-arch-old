package dev.lucasnlm.arch.soc

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.lucasnlm.arch.cpu.MockCpuInfo

import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.roundToInt

@RunWith(AndroidJUnit4::class)
class SocInfoFragmentTest {

    @Test
    fun testCpuBasicInfoAreVisible() {
        launchFragmentInContainer<SocInfoFragment>()

        MockCpuInfo.mockCpuInfo1.second.run {
            model?.isDisplayed()
            modelName?.isDisplayed()
            MockCpuInfo.mockAbi.isDisplayed()
            MockCpuInfo.mockGovernor.isDisplayed()
            cpuCores.toString().isDisplayed()
            revision.toString().isDisplayed()

            clocks.forEach {
                val clock = it.toDouble().roundToInt()

                if (it == 0) {
                    "0 MHz".isNotDisplayed()
                } else {
                    "$clock MHz".isDisplayed()
                }
            }

            flags.forEach {
                it.toUpperCase().isDisplayed()
            }
        }
    }

    private fun String.isDisplayed() =
        onView(withText(this)).check(matches(ViewMatchers.isDisplayed()))

    private fun String.isNotDisplayed() =
        onView(withText(this)).check(doesNotExist())
}
