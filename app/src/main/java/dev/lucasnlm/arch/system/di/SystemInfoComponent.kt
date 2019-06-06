package dev.lucasnlm.arch.system.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.core.di.DeviceModule
import dev.lucasnlm.arch.core.di.scope.FragmentScope
import dev.lucasnlm.arch.system.SystemInfoFragment

@Component(modules = [
    AndroidSupportInjectionModule::class,
    DeviceModule::class,
    SystemInfoModule::class
])
@FragmentScope
interface SystemInfoComponent {

    fun inject(systemInfoFragment: SystemInfoFragment)

    @Component.Builder
    interface Builder {

        fun commonModule(module: DeviceModule): Builder

        fun build(): SystemInfoComponent
    }
}
