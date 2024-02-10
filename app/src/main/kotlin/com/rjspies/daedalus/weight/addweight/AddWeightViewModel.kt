package com.rjspies.daedalus.weight.addweight

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.data.data.Weight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

private const val HANDLE_KEY_WEIGHT_VALUE = "HANDLE_KEY_WEIGHT_VALUE"

@KoinViewModel
class AddWeightViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val service: WeightService
) : ViewModel() {

    val weightValue: StateFlow<Float?> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_WEIGHT_VALUE,
        initialValue = null,
    )

    fun setWeightValue(value: Float?) {
        savedStateHandle[HANDLE_KEY_WEIGHT_VALUE] = value
    }

    fun saveWeight(weight: Weight) {
        viewModelScope.launch(Dispatchers.IO) {
            service.saveWeight(weight)
        }
    }
}
