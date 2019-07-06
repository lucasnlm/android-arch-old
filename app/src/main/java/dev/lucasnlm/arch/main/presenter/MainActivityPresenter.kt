package dev.lucasnlm.arch.main.presenter

import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.core.presenter.BasePresenter
import dev.lucasnlm.arch.main.Contracts
import dev.lucasnlm.arch.main.interactor.MainActivityInteractor
import dev.lucasnlm.arch.main.view.MainActivityView

open class MainActivityPresenter(
    private val mainActivityInteractor: MainActivityInteractor
): BasePresenter<MainActivityView>(), Contracts.Presenter {

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
        view?.setOnNavigationItemSelectedListener(null)
        super.onDestroy()
    }

    private fun replaceFragment(fragmentInfo: Pair<Int, Fragment>) {
        view?.run {
            setTitle(fragmentInfo.first)
            replaceFragment(fragmentInfo.second)
        }
    }

    private fun loadFragment(fragmentId: FragmentId) {
        mainActivityInteractor.loadFragment(fragmentId).subscribe(::replaceFragment).addToDisposables()
    }
}
