package dev.lucasnlm.arch.soc

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.lucasnlm.arch.core.di.DaggerAppComponent
import dev.lucasnlm.arch.soc.SocInfoFragment
import dev.lucasnlm.arch.soc.di.DaggerSocInfoComponent

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SocInfoFragmentTest {

    @Test
    fun useAppContext() {
        launchFragmentInContainer<SocInfoFragment>()
    }
}
