package dev.lucasnlm.arch.core.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.MainApplication
import dev.lucasnlm.arch.core.di.scope.AppScope
import dev.lucasnlm.arch.system.di.SystemModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    DeviceModule::class,
    SystemModule::class,
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
