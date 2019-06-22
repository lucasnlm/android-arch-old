package dev.lucasnlm.arch.main.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.lucasnlm.arch.core.di.scope.ActivityScope
import dev.lucasnlm.arch.main.MainActivity

@Component(modules = [
    AndroidSupportInjectionModule::class,
    MainActivityModule::class
])
@ActivityScope
interface MainActivityComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun mainActivityModule(module: MainActivityModule): Builder

        fun build(): MainActivityComponent
    }
}
