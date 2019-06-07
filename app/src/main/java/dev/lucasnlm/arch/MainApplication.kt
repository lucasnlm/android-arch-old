package dev.lucasnlm.arch

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dev.lucasnlm.arch.core.di.DaggerAppComponent

class MainApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()
}
