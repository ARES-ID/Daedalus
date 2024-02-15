package com.rjspies.daedalus.domain

import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.data.data.Weight
import org.koin.core.annotation.Factory
import kotlinx.coroutines.flow.Flow

@Factory
public class GetWeightsDescendingUseCase(private val service: WeightService) : UseCase<Flow<List<Weight>>> {
    override fun perform(): Flow<List<Weight>> = service.weightsDescending()
}
