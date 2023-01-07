package me.renespies.daedalus.addweight

import me.renespies.daedalus.addweight.data.Weight
import me.renespies.daedalus.addweight.data.WeightDao

class AddWeightService(private val weightDao: WeightDao) {
    suspend fun saveWeight(weight: Weight) = weightDao.insert(weight)
}
