package dev.lucasnlm.arch

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dev.lucasnlm.arch.core.di.DaggerTestAppComponent

class TestMainApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerTestAppComponent.builder().application(this).build()
}
