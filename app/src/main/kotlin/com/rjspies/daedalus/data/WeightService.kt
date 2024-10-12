package com.rjspies.daedalus.data

import kotlinx.coroutines.flow.Flow

public class WeightService(
    private val weightDao: WeightDao,
) {
    public suspend fun insertWeight(weight: Weight): Unit = weightDao.insert(weight)
    public fun weightsDescending(): Flow<List<Weight>> = weightDao.weightsDescending()
    public fun weightsAscending(): Flow<List<Weight>> = weightDao.weightsAscending()
    public suspend fun deleteWeight(weight: Weight): Unit = weightDao.deleteWeight(weight.id)
}
