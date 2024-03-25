package com.rjspies.daedalus.domain

import com.rjspies.daedalus.data.WeightService
import org.koin.core.annotation.Factory

@Factory
public class DeleteWeightUseCase(private val service: WeightService) {
    public suspend operator fun invoke(weight: Weight): Unit = service.deleteWeight(weight.toDataWeight())
}
