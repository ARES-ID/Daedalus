package com.rjspies.daedalus.weight.service

import com.rjspies.daedalus.weight.service.data.Weight
import com.rjspies.daedalus.weight.service.data.WeightDao

class WeightService(private val weightDao: WeightDao) {
    suspend fun saveWeight(weight: Weight) = weightDao.insert(weight)
    fun weights() = weightDao.weights()
    suspend fun deleteWeight(weight: Weight) = weightDao.deleteWeight(weight.id)
}
