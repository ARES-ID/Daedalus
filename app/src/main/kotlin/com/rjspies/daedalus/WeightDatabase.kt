package com.rjspies.daedalus

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Weight::class], version = 1)
public abstract class WeightDatabase : RoomDatabase() {
    public abstract fun weightDao(): WeightDao

    public companion object {
        @Volatile
        private var INSTANCE: WeightDatabase? = null

        public fun getDatabase(context: Context): WeightDatabase = INSTANCE ?: synchronized(this) {
            val instance = Room
                .databaseBuilder(
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
