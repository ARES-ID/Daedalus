package me.renespies.daedalus

import android.app.Application
import me.renespies.daedalus.addweight.AddWeightService
import me.renespies.daedalus.addweight.data.WeightDao
import me.renespies.daedalus.database.WeightDatabase
import timber.log.Timber

class MainApplication : Application() {
    private val weightDatabase
        get() = WeightDatabase.getDatabase(this)

    private val weightDao: WeightDao
        get() = weightDatabase.weightDao()

    val addWeightService by lazy {
        AddWeightService(weightDao)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
