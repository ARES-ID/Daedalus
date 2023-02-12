package me.renespies.daedalus.weight.service.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(weight: Weight)

    @Query("SELECT * FROM Weight")
    fun weights(): Flow<List<Weight>>

    @Query("DELETE FROM Weight")
    suspend fun clear()
}
