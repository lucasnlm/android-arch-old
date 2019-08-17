package dev.lucasnlm.arch.phone.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.core.di.CoreModule
import dev.lucasnlm.arch.core.di.scope.FragmentScope
import dev.lucasnlm.arch.phone.PhoneInfoFragment

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        PhoneInfoModule::class
    ]
)
@FragmentScope
interface PhoneInfoComponent {

    fun inject(deviceInfoFragment: PhoneInfoFragment)

    @Component.Builder
    interface Builder {

        fun coreModule(module: CoreModule): Builder

        fun build(): PhoneInfoComponent
    }

}
