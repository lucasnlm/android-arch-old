package dev.lucasnlm.arch.main.view

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.lucasnlm.arch.R
import dev.lucasnlm.arch.main.Contracts

open class MainActivityView: Contracts.View() {

    private lateinit var container: FrameLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onViewCreated(view: View) {
        bottomNavigationView = view.findViewById(R.id.nav_view)
        container = view.findViewById(R.id.container)
    }

    override fun setOnNavigationItemSelectedListener(listener: BottomNavigationView.OnNavigationItemSelectedListener?) {
        bottomNavigationView.setOnNavigationItemSelectedListener(listener)
    }

    override fun replaceFragment(fragment: Fragment) {
        activity?.run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }

    override fun setTitle(@StringRes title: Int) {
        activity?.run {
            setTitle(getString(title))
        }
    }
}
