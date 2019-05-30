package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.lucasnlm.arch.soc.SocInfoFragment
import dev.lucasnlm.arch.soc.di.SocInfoModule
import javax.inject.Singleton

@Module
abstract class FragmentBuilderModule {
    @Singleton
    @ContributesAndroidInjector(modules = [
        SocInfoModule::class
    ])
    abstract fun bindSocInfoFragment(): SocInfoFragment
}
