package com.getmega.getmegaapp

import android.app.Application
import com.getmega.koinmodule.di.apiModule
import com.getmega.koinmodule.di.repositoryModule
import com.getmega.koinmodule.di.useCaseModule
import com.getmega.getmegaapp.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    presentationModule,
                    repositoryModule,
                    useCaseModule,
                    apiModule,
                )
            )
        }
    }
}