package dev.lucasnlm.arch.soc.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.core.di.DeviceModule
import dev.lucasnlm.arch.core.di.scope.FragmentScope
import dev.lucasnlm.arch.soc.SocInfoFragment

@Component(modules = [
    AndroidSupportInjectionModule::class,
    DeviceModule::class,
    SocInfoModule::class
])
@FragmentScope
interface SocInfoComponent {

    fun inject(socInfoFragment: SocInfoFragment)

    @Component.Builder
    interface Builder {
        fun commonModule(module: DeviceModule): Builder

        fun build(): SocInfoComponent
    }
}
