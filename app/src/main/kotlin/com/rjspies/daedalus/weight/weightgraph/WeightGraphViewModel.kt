package com.rjspies.daedalus.weight.weightgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.weight.service.data.Weight
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WeightGraphViewModel(weightService: WeightService) : ViewModel() {
    val weights: StateFlow<List<Weight>> = weightService.weights().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList(),
    )
}
