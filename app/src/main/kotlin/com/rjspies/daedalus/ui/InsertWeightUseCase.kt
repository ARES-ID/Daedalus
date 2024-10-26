package com.rjspies.daedalus.ui

import com.rjspies.daedalus.data.Weight
import com.rjspies.daedalus.data.WeightService

public class InsertWeightUseCase(
    private val service: WeightService,
) {
    public suspend operator fun invoke(weight: Weight): Unit = service.insertWeight(weight)
}
