package dev.lucasnlm.arch.main.interactor

import androidx.fragment.app.Fragment
import dev.lucasnlm.arch.phone.PhoneInfoFragment
import dev.lucasnlm.arch.main.presenter.FragmentId
import dev.lucasnlm.arch.soc.SocInfoFragment
import dev.lucasnlm.arch.system.SystemInfoFragment
import io.reactivex.Single
import org.junit.Test

class MainActivityInteractorTest {

    private fun loadFragment(fragmentId: FragmentId): Single<Pair<Int, Fragment>> =
        MainActivityInteractor().loadFragment(fragmentId)

    @Test
    fun testMainActivityInteractor() {
        loadFragment(FragmentId.SocInfo).test().assertValue {
            it.second is SocInfoFragment
        }

        loadFragment(FragmentId.System).test().assertValue {
            it.second is SystemInfoFragment
        }

        loadFragment(FragmentId.DeviceInfo).test().assertValue {
            it.second is PhoneInfoFragment
        }
    }
}
