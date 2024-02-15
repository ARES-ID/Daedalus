package com.rjspies.daedalus.domain

import com.rjspies.daedalus.data.WeightService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
public class GetWeightsAscendingUseCase(private val service: WeightService) {
    public operator fun invoke(): Flow<List<Weight>> = service.weightsAscending().map { it.map { it.convert() } }
}
