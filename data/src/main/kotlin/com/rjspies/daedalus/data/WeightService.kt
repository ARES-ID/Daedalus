package com.rjspies.daedalus.data

import com.rjspies.daedalus.data.data.Weight
import com.rjspies.daedalus.data.data.WeightDao
import org.koin.core.annotation.Single
import kotlinx.coroutines.flow.Flow

@Single
public class WeightService(private val weightDao: WeightDao) {
    public suspend fun saveWeight(weight: Weight): Unit = weightDao.insert(weight)
    public fun weights(): Flow<List<Weight>> = weightDao.weights()
    public suspend fun deleteWeight(weight: Weight): Unit = weightDao.deleteWeight(weight.id)
}