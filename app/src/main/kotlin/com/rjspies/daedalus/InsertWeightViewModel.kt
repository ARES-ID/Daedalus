package com.rjspies.daedalus

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.koin.android.annotation.KoinViewModel
import java.time.ZonedDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@KoinViewModel
internal class InsertWeightViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val insertWeightUseCase: com.rjspies.daedalus.InsertWeightUseCase,
) : ViewModel() {
    val uiState: StateFlow<InsertWeightUiState> = savedStateHandle.getStateFlow(
        key = SAVED_STATE_HANDLE_KEY_UI_STATE,
        initialValue = InsertWeightUiState(),
    )

    fun setDismissDialog(dismissDialog: () -> Unit) {
        savedStateHandle[SAVED_STATE_HANDLE_KEY_UI_STATE] = uiState.value.copy(dismissDialog = dismissDialog)
    }

    fun setError(error: com.rjspies.daedalus.AddWeightError?) {
        savedStateHandle[SAVED_STATE_HANDLE_KEY_UI_STATE] = uiState.value.copy(error = error)
    }

    private fun setIsLoading(isLoading: Boolean) {
        savedStateHandle[SAVED_STATE_HANDLE_KEY_UI_STATE] = uiState.value.copy(isLoading = isLoading)
    }

    fun insertWeight(weightValue: String) {
        val job = viewModelScope.launch(Dispatchers.IO) {
            setError(null)
            setIsLoading(true)
            val parsedWeightValue = weightValue.parseToFloat()
            if (parsedWeightValue != null) {
                val weight = Weight(value = parsedWeightValue, note = null, dateTime = ZonedDateTime.now())
                insertWeightUseCase(weight)
                uiState.value.dismissDialog()
            } else {
                setError(com.rjspies.daedalus.AddWeightError.ParseFloatError)
            }
        }

        job.invokeOnCompletion {
            setIsLoading(false)
        }
    }
}

private fun String.parseToFloat(): Float? = replace(",", ".").toFloatOrNull()
