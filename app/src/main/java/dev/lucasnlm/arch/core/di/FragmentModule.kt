package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.lucasnlm.arch.phone.PhoneInfoFragment
import dev.lucasnlm.arch.phone.di.PhoneInfoModule
import dev.lucasnlm.arch.soc.SocInfoFragment
import dev.lucasnlm.arch.soc.di.SocInfoModule
import dev.lucasnlm.arch.system.SystemInfoFragment
import dev.lucasnlm.arch.system.di.SystemInfoModule

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(
        modules = [ SocInfoModule::class ]
    )
    abstract fun contributeSocInfoFragment(): SocInfoFragment

    @ContributesAndroidInjector(
        modules = [ SystemInfoModule::class ]
    )
    abstract fun contributeSystemInfoFragment(): SystemInfoFragment

    @ContributesAndroidInjector(
        modules = [ PhoneInfoModule::class ]
    )
    abstract fun contributeDeviceInfoFragment(): PhoneInfoFragment
}
