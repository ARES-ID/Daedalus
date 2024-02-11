package com.rjspies.daedalus.ui.addweight

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.common.AddWeightError
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.data.data.Weight
import com.rjspies.daedalus.ui.common.SAVED_STATE_HANDLE_KEY_UI_STATE
import org.koin.android.annotation.KoinViewModel
import timber.log.Timber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@KoinViewModel
internal class AddWeightViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val service: WeightService,
) : ViewModel() {
    val uiState: StateFlow<AddWeightUiState> = savedStateHandle.getStateFlow(
        key = SAVED_STATE_HANDLE_KEY_UI_STATE,
        initialValue = AddWeightUiState(),
    )

    fun setDismissDialog(dismissDialog: () -> Unit) {
        savedStateHandle[SAVED_STATE_HANDLE_KEY_UI_STATE] = uiState.value.copy(dismissDialog = dismissDialog)
    }

    fun setError(error: AddWeightError?) {
        savedStateHandle[SAVED_STATE_HANDLE_KEY_UI_STATE] = uiState.value.copy(error = error)
    }

    private fun setIsLoading(isLoading: Boolean) {
        savedStateHandle[SAVED_STATE_HANDLE_KEY_UI_STATE] = uiState.value.copy(isLoading = isLoading)
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
                uiState.value.dismissDialog()
            } else {
                setError(AddWeightError.ParseFloatError)
            }
        }

        job.invokeOnCompletion {
            setIsLoading(false)
        }
    }
}

private fun String.parseToFloat(): Float? = replace(",", ".").toFloatOrNull()
