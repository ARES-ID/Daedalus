package com.rjspies.daedalus.data.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
public interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    public suspend fun insert(weight: Weight)

    @Query("SELECT * FROM Weight ORDER BY dateTime ASC")
    public fun weights(): Flow<List<Weight>>

    @Query("DELETE FROM Weight WHERE id = :id")
    public suspend fun deleteWeight(id: Int)
}
