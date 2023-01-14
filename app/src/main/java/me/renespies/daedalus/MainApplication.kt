package me.renespies.daedalus

import android.app.Application
import me.renespies.daedalus.database.WeightDatabase
import me.renespies.daedalus.weight.service.WeightService
import me.renespies.daedalus.weight.service.data.WeightDao
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
    }
}
