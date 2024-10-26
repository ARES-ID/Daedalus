package com.rjspies.daedalus.ui.insertweight

import com.rjspies.daedalus.data.Weight
import com.rjspies.daedalus.data.WeightService

class InsertWeightUseCase(
    private val service: WeightService,
) {
    suspend operator fun invoke(weight: Weight): Unit = service.insertWeight(weight)
}
