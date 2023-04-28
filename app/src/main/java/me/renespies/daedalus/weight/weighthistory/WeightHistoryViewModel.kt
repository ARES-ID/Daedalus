package me.renespies.daedalus.weight.weighthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import me.renespies.daedalus.compose.requireApplication
import me.renespies.daedalus.weight.service.WeightService
import me.renespies.daedalus.weight.service.data.Weight

class WeightHistoryViewModel private constructor(private val service: WeightService) : ViewModel() {
    val weights = service.weights().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    suspend fun deleteWeight(weight: Weight) = service.deleteWeight(weight)

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = requireApplication()
                WeightHistoryViewModel(application.weightService)
            }
        }
    }
}
