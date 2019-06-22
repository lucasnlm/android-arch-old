package dev.lucasnlm.arch.device.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.core.di.scope.FragmentScope
import dev.lucasnlm.arch.device.DeviceInfoFragment

@Component(modules = [
    AndroidSupportInjectionModule::class,
    DeviceInfoModule::class
])
@FragmentScope
interface DeviceInfoComponent {

    fun inject(deviceInfoFragment: DeviceInfoFragment)

    @Component.Builder
    interface Builder {
        fun deviceInfoModule(module: DeviceInfoModule): Builder

        fun build(): DeviceInfoComponent
    }

}
