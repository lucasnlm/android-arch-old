package dev.lucasnlm.arch

import android.os.Bundle
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dev.lucasnlm.arch.device.DeviceInfoFragment
import dev.lucasnlm.arch.soc.SocInfoFragment
import dev.lucasnlm.arch.system.SystemInfoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var container: FrameLayout

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_cpu -> {
                loadFragment(FragmentId.SOC_INFO_FRAGMENT)
                true
            }
            R.id.navigation_memory -> {
                loadFragment(FragmentId.SYSTEM_INFO_FRAGMENT)
                true
            }
            R.id.navigation_system -> {
                loadFragment(FragmentId.DEVICE_INFO_FRAGMENT)
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
        val fragment: Fragment = when(fragmentId) {
            FragmentId.SOC_INFO_FRAGMENT -> SocInfoFragment()
            FragmentId.SYSTEM_INFO_FRAGMENT -> SystemInfoFragment()
            FragmentId.DEVICE_INFO_FRAGMENT -> DeviceInfoFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private enum class FragmentId {
        SOC_INFO_FRAGMENT,
        SYSTEM_INFO_FRAGMENT,
        DEVICE_INFO_FRAGMENT
    }
}
