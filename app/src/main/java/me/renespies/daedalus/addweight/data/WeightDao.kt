package me.renespies.daedalus.addweight.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(weight: Weight)
}
