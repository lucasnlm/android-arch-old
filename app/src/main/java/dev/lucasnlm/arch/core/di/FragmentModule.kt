package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.lucasnlm.arch.soc.SocInfoFragment
import dev.lucasnlm.arch.soc.di.SocInfoModule

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(
        modules = [ SocInfoModule::class ]
    )
    abstract fun contributeSocInfoFragment(): SocInfoFragment
}
