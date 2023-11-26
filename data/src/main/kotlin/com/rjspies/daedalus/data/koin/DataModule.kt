package com.rjspies.daedalus.data.koin

import android.content.Context
import com.rjspies.daedalus.data.WeightDatabase
import com.rjspies.daedalus.data.data.WeightDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.rjspies.daedalus.data")
public class DataModule {
    @Single
    public fun provideWeightDatabase(context: Context): WeightDatabase {
        return WeightDatabase.getDatabase(context)
    }

    @Single
    public fun provideWeightDao(database: WeightDatabase): WeightDao {
        return database.weightDao()
    }
}
