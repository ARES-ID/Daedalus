package com.rjspies.daedalus.domain

import com.rjspies.daedalus.data.Weight
import com.rjspies.daedalus.data.WeightService

public class DeleteWeightUseCase(
    private val service: WeightService,
) {
    public suspend operator fun invoke(weight: Weight): Unit = service.deleteWeight(weight)
}
