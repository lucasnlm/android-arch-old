package dev.lucasnlm.arch.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule

@Module(
    includes = [
        AndroidSupportInjectionModule::class
    ]
)
open class CoreModule {

    private lateinit var application: Application

    fun setup(application: Application) = this.apply {
        this.application = application
    }

    @Provides
    fun provideApplication(): Application = application

    @Provides
    fun provideContext(): Context = application.applicationContext
}
