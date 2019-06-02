package dev.lucasnlm.arch.core.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.TestMainApplication
import dev.lucasnlm.arch.core.di.scope.AppScope

@Component(modules = [
    AndroidSupportInjectionModule::class,
    DeviceModule::class,
    FragmentModule::class
])
@AppScope
interface TestAppComponent : AndroidInjector<TestMainApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: TestMainApplication): Builder

        fun build(): TestAppComponent
    }
}
