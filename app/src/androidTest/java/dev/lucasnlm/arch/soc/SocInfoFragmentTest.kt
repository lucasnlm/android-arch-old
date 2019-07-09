package dev.lucasnlm.arch.soc

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.cpu.MockCpuInfo
import dev.lucasnlm.arch.helper.isVisible
import dev.lucasnlm.arch.helper.isInfoListDisplayed
import dev.lucasnlm.arch.helper.waitFor
import dev.lucasnlm.arch.soc.model.CpuInfo
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SocInfoFragmentTest {

    @Test
    fun testSocInfoAreDisplayed() {
        launchFragmentInContainer<SocInfoFragment>()
        val mockedCpu: CpuInfo = MockCpuInfo.mockCpuInfo1.second

        // Wait one second to make sure it got GL info.
        waitFor(1000)

        isInfoListDisplayed(R.id.details_list, mapOf(
            "ABI" to mockedCpu.abi,
            "Model" to mockedCpu.model!!,
            "Cores" to mockedCpu.cpuCores,
            "Revision" to mockedCpu.revision!!,
            "Governor" to mockedCpu.governor!!
        ))

        isInfoListDisplayed(R.id.clock_list, mapOf(
            "Min" to "500 MHz",
            "Max" to "1500 MHz",
            "Core 0" to "1000 MHz",
            "Core 1" to "1500 MHz",
            "Core 2" to "500 MHz",
            "Core 3" to "Inactive"
        ))

        isInfoListDisplayed(R.id.gpu_list, listOf(
            "Vendor",
            "Renderer",
            "Version"
        ))

        mockedCpu.flags.forEach {
            it.toUpperCase().isVisible()
        }
    }
}
