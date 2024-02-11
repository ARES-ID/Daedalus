package com.rjspies.daedalus.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.common.AddWeightError
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.data.data.Weight
import com.rjspies.daedalus.ui.common.HANDLE_KEY_UI_STATE
import org.koin.android.annotation.KoinViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@KoinViewModel
internal class AddWeightViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val service: WeightService,
) : ViewModel() {
    val uiState: StateFlow<AddWeightUiState> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_UI_STATE,
        initialValue = AddWeightUiState(),
    )

    fun setDismissDialog(dismissDialog: () -> Unit) {
        savedStateHandle[HANDLE_KEY_UI_STATE] = uiState.value.copy(dismissDialog = dismissDialog)
    }

    private fun setError(error: AddWeightError?) {
        savedStateHandle[HANDLE_KEY_UI_STATE] = uiState.value.copy(error = error)
    }

    private fun setIsLoading(isLoading: Boolean) {
        savedStateHandle[HANDLE_KEY_UI_STATE] = uiState.value.copy(isLoading = isLoading)
    }

    fun saveWeight(weightValue: String) {
        val job = viewModelScope.launch(Dispatchers.IO) {
            setError(null)
            setIsLoading(true)
            val parsedWeightValue = weightValue.parseToFloat()
            if (parsedWeightValue != null) {
                val weight = Weight(
                    value = parsedWeightValue,
                    note = null,
                )
                service.saveWeight(weight)
            } else {
                setError(AddWeightError.ParseFloatError)
            }
        }

        job.invokeOnCompletion {
            setIsLoading(false)
            uiState.value.dismissDialog()
        }
    }
}

private fun String.parseToFloat(): Float? = replace(",", ".").toFloatOrNull()
