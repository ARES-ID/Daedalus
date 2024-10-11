package com.rjspies.daedalus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@KoinViewModel
internal class WeightGraphViewModel(
    getWeightsAscendingUseCase: com.rjspies.daedalus.GetWeightsAscendingUseCase,
) : ViewModel() {
    val weights = getWeightsAscendingUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )
}
