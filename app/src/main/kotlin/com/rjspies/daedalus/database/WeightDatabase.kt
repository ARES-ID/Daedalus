package com.rjspies.daedalus.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rjspies.daedalus.weight.service.data.Weight
import com.rjspies.daedalus.weight.service.data.WeightDao

@Database(entities = [Weight::class], version = 1)
abstract class WeightDatabase : RoomDatabase() {
    abstract fun weightDao(): WeightDao

    companion object {
        @Volatile
        private var INSTANCE: WeightDatabase? = null

        fun getDatabase(context: Context): WeightDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    WeightDatabase::class.java,
                    "weight_database",
                ).apply {
                    fallbackToDestructiveMigration()
                }.build()
                INSTANCE = instance
                instance
            }
        }
    }
}
