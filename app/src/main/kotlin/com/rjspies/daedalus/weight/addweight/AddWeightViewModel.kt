package com.rjspies.daedalus.weight.addweight

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.AddWeightError
import com.rjspies.daedalus.HANDLE_KEY_UI_STATE
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.data.data.Weight
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@KoinViewModel
class AddWeightViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val service: WeightService
) : ViewModel() {
    val uiState: StateFlow<AddWeightUiState> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_UI_STATE,
        initialValue = AddWeightUiState(),
    )

    fun setError(error: AddWeightError?) {
        savedStateHandle[HANDLE_KEY_UI_STATE] = uiState.value.copy(error = error)
    }

    fun setDismissDialog(dismissDialog: () -> Unit) {
        savedStateHandle[HANDLE_KEY_UI_STATE] = uiState.value.copy(dismissDialog = dismissDialog)
    }

    private fun setIsLoading(isLoading: Boolean) {
        savedStateHandle[HANDLE_KEY_UI_STATE] = uiState.value.copy(isLoading = isLoading)
    }

    fun saveWeight(weight: Weight) {
        val job = viewModelScope.launch(Dispatchers.IO) {
            setIsLoading(true)
            service.saveWeight(weight)
        }
        job.invokeOnCompletion {
            setIsLoading(false)
            uiState.value.dismissDialog()
        }
    }
}

