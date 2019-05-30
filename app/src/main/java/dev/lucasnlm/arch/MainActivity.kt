package dev.lucasnlm.arch

import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import dev.lucasnlm.arch.soc.SocInfoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_cpu -> {
                loadFragment(FragmentId.SOC_INFO_FRAGMENT)
                true
            }
            R.id.navigation_memory -> {
                loadFragment(FragmentId.SOC_INFO_FRAGMENT)
                true
            }
            R.id.navigation_system -> {
                loadFragment(FragmentId.SOC_INFO_FRAGMENT)
                true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.nav_view).apply {
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }

        container = findViewById(R.id.container)

        loadFragment(FragmentId.SOC_INFO_FRAGMENT)
    }

    private fun loadFragment(fragmentId: FragmentId) {
        val fragment = when(fragmentId) {
            FragmentId.SOC_INFO_FRAGMENT -> SocInfoFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private enum class FragmentId {
        SOC_INFO_FRAGMENT
    }
}
