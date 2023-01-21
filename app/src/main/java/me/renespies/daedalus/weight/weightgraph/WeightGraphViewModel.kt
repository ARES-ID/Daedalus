package me.renespies.daedalus.weight.weightgraph

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import me.renespies.daedalus.MainApplication
import me.renespies.daedalus.weight.service.WeightService
import me.renespies.daedalus.weight.service.data.Weight

class WeightGraphViewModel private constructor(private val weightService: WeightService) : ViewModel() {
    val weights: StateFlow<List<Weight>> = weightService.weights().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = get(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY) as MainApplication
                WeightGraphViewModel(application.weightService)
            }
        }
    }
}
