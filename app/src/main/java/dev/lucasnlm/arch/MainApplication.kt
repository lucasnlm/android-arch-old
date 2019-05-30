package dev.lucasnlm.arch

import dagger.android.support.DaggerApplication
import dev.lucasnlm.arch.core.di.DaggerAppComponent

class MainApplication: DaggerApplication() {

    private val applicationInjector = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun applicationInjector() = applicationInjector
}
