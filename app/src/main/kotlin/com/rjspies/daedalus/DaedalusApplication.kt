package com.rjspies.daedalus

import android.app.Application
import com.rjspies.daedalus.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import timber.log.Timber

class DaedalusApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin()
    }

    private fun startKoin(): KoinApplication = startKoin {
        androidLogger()
        androidContext(applicationContext)
        modules(appModule)
    }
}
