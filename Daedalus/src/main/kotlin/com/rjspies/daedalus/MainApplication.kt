package com.rjspies.daedalus

import android.app.Application
import com.rjspies.daedalus.database.WeightDatabase
import com.rjspies.daedalus.koin.AppModule
import com.rjspies.daedalus.weight.service.WeightService
import com.rjspies.daedalus.weight.service.data.WeightDao
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import timber.log.Timber

class MainApplication : Application() {
    private val weightDatabase
        get() = WeightDatabase.getDatabase(this)

    private val weightDao: WeightDao
        get() = weightDatabase.weightDao()

    val weightService by lazy {
        WeightService(weightDao)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin()
    }

    private fun startKoin() = startKoin {
        androidLogger()
        androidContext(applicationContext)
        modules(AppModule().module)
    }
}
