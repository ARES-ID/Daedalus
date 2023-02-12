package me.renespies.daedalus.weight.service

import me.renespies.daedalus.weight.service.data.Weight
import me.renespies.daedalus.weight.service.data.WeightDao

class WeightService(private val weightDao: WeightDao) {
    suspend fun saveWeight(weight: Weight) = weightDao.insert(weight)
    fun weights() = weightDao.weights()
    suspend fun clearWeights() = weightDao.clear()
}
