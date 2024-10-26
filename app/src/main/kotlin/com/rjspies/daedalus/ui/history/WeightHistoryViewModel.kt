package com.rjspies.daedalus.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.data.Weight
import com.rjspies.daedalus.domain.DeleteWeightUseCase
import com.rjspies.daedalus.domain.GetWeightsDescendingUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

internal class WeightHistoryViewModel(
    getWeightsDescendingUseCase: GetWeightsDescendingUseCase,
    private val deleteWeightUseCase: DeleteWeightUseCase,
) : ViewModel() {
    val weights = getWeightsDescendingUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )

    suspend fun deleteWeight(weight: Weight) = deleteWeightUseCase(weight)
}
