package com.rjspies.daedalus.weight.service.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(weight: Weight)

    @Query("SELECT * FROM Weight ORDER BY dateTime ASC")
    fun weights(): Flow<List<Weight>>

    @Query("DELETE FROM Weight WHERE id = :id")
    suspend fun deleteWeight(id: Int)
}
