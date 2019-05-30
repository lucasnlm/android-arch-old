package dev.lucasnlm.arch.soc.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.soc.SocInfoFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    SocInfoModule::class
])
interface SocInfoComponent {

    fun inject(socInfoFragment: SocInfoFragment)

}
