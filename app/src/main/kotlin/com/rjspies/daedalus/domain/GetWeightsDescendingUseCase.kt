package com.rjspies.daedalus.domain

import com.rjspies.daedalus.data.Weight
import com.rjspies.daedalus.data.WeightService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class GetWeightsDescendingUseCase(
    private val service: WeightService,
) {
    public operator fun invoke(): Flow<List<Weight>> = service.weightsDescending().map { it.map { it } }
}
