package com.rjspies.daedalus.domain

import com.rjspies.daedalus.data.WeightService
import org.koin.core.annotation.Factory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Factory
public class GetWeightsDescendingUseCase(private val service: WeightService) {
    public operator fun invoke(): Flow<List<Weight>> = service.weightsDescending().map { it.map { it.toWeight() } }
}
