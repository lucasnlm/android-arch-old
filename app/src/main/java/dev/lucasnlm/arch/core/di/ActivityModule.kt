package dev.lucasnlm.arch.core.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.lucasnlm.arch.main.MainActivity
import dev.lucasnlm.arch.main.di.MainActivityModule

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(
        modules = [ MainActivityModule::class ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
