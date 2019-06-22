package dev.lucasnlm.arch.main

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import dev.lucasnlm.arch.core.view.BaseActivityView

interface Contracts {

    abstract class View: BaseActivityView() {
        abstract fun replaceFragment(fragment: Fragment)
        abstract fun setOnNavigationItemSelectedListener(listener: OnNavigationItemSelectedListener?)
        abstract fun setTitle(@StringRes title: Int)
    }

    interface Presenter
}
