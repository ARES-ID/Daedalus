package com.rjspies.daedalus.weight.addweight

import androidx.lifecycle.ViewModel
import com.rjspies.daedalus.weight.service.WeightService
import com.rjspies.daedalus.weight.service.data.Weight
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AddWeightViewModel(private val service: WeightService) : ViewModel() {
    suspend fun saveWeight(weight: Weight) = service.saveWeight(weight)
}
