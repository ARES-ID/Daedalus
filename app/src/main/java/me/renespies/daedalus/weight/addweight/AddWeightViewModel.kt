package me.renespies.daedalus.weight.addweight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import me.renespies.daedalus.MainApplication
import me.renespies.daedalus.weight.service.WeightService
import me.renespies.daedalus.weight.service.data.Weight

class AddWeightViewModel private constructor(private val service: WeightService) : ViewModel() {
    suspend fun saveWeight(weight: Weight) = service.saveWeight(weight)

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = get(APPLICATION_KEY) as MainApplication
                AddWeightViewModel(application.weightService)
            }
        }
    }
}
