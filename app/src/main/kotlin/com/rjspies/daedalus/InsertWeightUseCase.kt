package com.rjspies.daedalus

import org.koin.core.annotation.Factory

@Factory
public class InsertWeightUseCase(
    private val service: com.rjspies.daedalus.WeightService,
) {
    public suspend operator fun invoke(weight: Weight): Unit = service.insertWeight(weight)
}
