package me.renespies.daedalus.weight.weighthistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import me.renespies.daedalus.MainApplication
import me.renespies.daedalus.weight.service.WeightService

class WeightHistoryViewModel private constructor(private val service: WeightService) : ViewModel() {
    val weights = service.weights().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = get(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY) as MainApplication
                WeightHistoryViewModel(application.weightService)
            }
        }
    }
}
