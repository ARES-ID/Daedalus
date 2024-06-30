package com.rjspies.daedalus.ui.weightgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.domain.GetWeightsAscendingUseCase
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@KoinViewModel
internal class WeightGraphViewModel(
    getWeightsAscendingUseCase: GetWeightsAscendingUseCase,
) : ViewModel() {
    val weights = getWeightsAscendingUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )
}
