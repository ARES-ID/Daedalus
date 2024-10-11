package com.rjspies.daedalus

import org.koin.core.annotation.Factory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Factory
public class GetWeightsDescendingUseCase(
    private val service: com.rjspies.daedalus.WeightService,
) {
    public operator fun invoke(): Flow<List<Weight>> = service.weightsDescending().map { it.map { it } }
}
