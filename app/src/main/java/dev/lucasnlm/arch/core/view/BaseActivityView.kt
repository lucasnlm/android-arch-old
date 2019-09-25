package dev.lucasnlm.arch.core.view

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.lucasnlm.arch.R

abstract class BaseActivityView : BaseView {
    private var activity: AppCompatActivity? = null

    fun setActivity(activity: AppCompatActivity) {
        this.activity = activity
    }

    fun freeActivity() {
        this.activity = null
    }

    fun replaceFragment(fragment: Fragment) {
        activity?.run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }

    fun setTitle(@StringRes title: Int)  {
        activity?.setTitle(title)
    }
}
