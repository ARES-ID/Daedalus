package me.renespies.daedalus.weight.weightgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import me.renespies.daedalus.compose.requireApplication
import me.renespies.daedalus.weight.service.WeightService
import me.renespies.daedalus.weight.service.data.Weight

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
