package dev.lucasnlm.arch.main.interactor

import androidx.fragment.app.Fragment
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.device.DeviceInfoFragment
import dev.lucasnlm.arch.main.presenter.FragmentId
import dev.lucasnlm.arch.soc.SocInfoFragment
import dev.lucasnlm.arch.system.SystemInfoFragment
import io.reactivex.Single

open class MainActivityInteractor {

    fun loadFragment(fragmentId: FragmentId): Single<Pair<Int, Fragment>> = Single.just(when(fragmentId) {
        FragmentId.SocInfo -> R.string.soc_title to SocInfoFragment()
        FragmentId.System -> R.string.sys_title to SystemInfoFragment()
        FragmentId.DeviceInfo -> R.string.dev_title to DeviceInfoFragment()
    })
}
