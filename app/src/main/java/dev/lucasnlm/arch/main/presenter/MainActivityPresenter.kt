package dev.lucasnlm.arch.main.presenter

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.device.DeviceInfoFragment
import dev.lucasnlm.arch.main.Contracts
import dev.lucasnlm.arch.main.view.MainActivityView
import dev.lucasnlm.arch.soc.SocInfoFragment
import dev.lucasnlm.arch.system.SystemInfoFragment

class MainActivityPresenter: BasePresenter<MainActivityView>(), Contracts.Presenter {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_cpu -> {
                loadFragment(FragmentId.SocInfo)
                true
            }
            R.id.navigation_system -> {
                loadFragment(FragmentId.System)
                true
            }
            R.id.navigation_device -> {
                loadFragment(FragmentId.DeviceInfo)
                true
            }
            else -> false
        }
    }

    override fun onCreate() {
        super.onCreate()
        loadFragment(FragmentId.SocInfo)
        view?.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        view?.setOnNavigationItemSelectedListener(null)
    }

    private fun loadFragment(fragmentId: FragmentId) {
        val fragment: Pair<Int, Fragment> = when(fragmentId) {
            FragmentId.SocInfo -> R.string.soc_title to SocInfoFragment()
            FragmentId.System -> R.string.sys_title to SystemInfoFragment()
            FragmentId.DeviceInfo -> R.string.dev_title to DeviceInfoFragment()
        }

        view?.run {
            setTitle(fragment.first)
            replaceFragment(fragment.second)
        }
    }
}
