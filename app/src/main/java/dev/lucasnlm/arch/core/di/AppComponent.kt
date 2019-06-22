package dev.lucasnlm.arch.core.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.MainApplication
import dev.lucasnlm.arch.core.di.scope.AppScope

@Component(modules = [
    AndroidSupportInjectionModule::class,
    DeviceModule::class,
    FragmentModule::class,
    ActivityModule::class
])
@AppScope
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MainApplication): Builder

        fun build(): AppComponent
    }
}
