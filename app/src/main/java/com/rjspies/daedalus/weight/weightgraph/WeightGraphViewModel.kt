package com.rjspies.daedalus.weight.weightgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.rjspies.daedalus.compose.requireApplication
import com.rjspies.daedalus.weight.service.WeightService
import com.rjspies.daedalus.weight.service.data.Weight
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class WeightGraphViewModel private constructor(private val weightService: WeightService) : ViewModel() {
    val weights: StateFlow<List<Weight>> = weightService.weights().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = requireApplication()
                WeightGraphViewModel(application.weightService)
            }
        }
    }
}
