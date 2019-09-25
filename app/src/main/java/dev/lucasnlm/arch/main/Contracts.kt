package dev.lucasnlm.arch.main

import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import dev.lucasnlm.arch.core.view.BaseActivityView

interface Contracts {

    abstract class View: BaseActivityView() {
        abstract fun setOnNavigationItemSelectedListener(listener: OnNavigationItemSelectedListener?)
    }

    interface Presenter
}
