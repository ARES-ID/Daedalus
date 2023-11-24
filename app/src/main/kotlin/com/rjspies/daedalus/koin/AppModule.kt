package com.rjspies.daedalus.koin

import android.content.Context
import com.rjspies.daedalus.data.WeightDatabase
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.weight.service.data.WeightDao
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

/**
 * This is a workaround because `@ComponentScan` will be the source of a compiler warning
 * and therefore fail the build due to `-Werror`.
 *
 * @see <a href="https://github.com/InsertKoinIO/koin-annotations/issues/79">Koin Issue</a>
 */
@Module
@ComponentScan("com.rjspies.daedalus")
class AppModule {
    @Single
    fun provideDatabase(context: Context): WeightDatabase {
        return WeightDatabase.getDatabase(context)
    }

    @Single
    fun provideWeightDao(database: WeightDatabase): WeightDao {
        return database.weightDao()
    }

    @Single
    fun provideWeightService(dao: WeightDao): WeightService {
        return WeightService(dao)
    }
}
