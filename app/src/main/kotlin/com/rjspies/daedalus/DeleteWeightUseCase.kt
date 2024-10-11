package com.rjspies.daedalus

import org.koin.core.annotation.Factory

@Factory
public class DeleteWeightUseCase(
    private val service: com.rjspies.daedalus.WeightService,
) {
    public suspend operator fun invoke(weight: Weight): Unit = service.deleteWeight(weight)
}
