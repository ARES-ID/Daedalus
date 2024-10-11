package com.rjspies.daedalus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@KoinViewModel
internal class WeightHistoryViewModel(
    getWeightsDescendingUseCase: com.rjspies.daedalus.GetWeightsDescendingUseCase,
    private val deleteWeightUseCase: com.rjspies.daedalus.DeleteWeightUseCase,
) : ViewModel() {
    val weights = getWeightsDescendingUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )

    suspend fun deleteWeight(weight: Weight) = deleteWeightUseCase(weight)
}
