package com.rjspies.daedalus.weight.addweight

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rjspies.daedalus.AddWeightError
import com.rjspies.daedalus.data.WeightService
import com.rjspies.daedalus.data.data.Weight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import org.koin.android.annotation.KoinViewModel

private const val HANDLE_KEY_UI_STATE = "HANDLE_KEY_UI_STATE"

@KoinViewModel
class AddWeightViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val service: WeightService
) : ViewModel() {
    val uiState: StateFlow<AddWeightUiState> = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_UI_STATE,
        initialValue = AddWeightUiState(),
    )

    fun setError(error: AddWeightError) {
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

@Parcelize
open class UiState : Parcelable

data class AddWeightUiState(
    val isLoading: Boolean = false,
    val error: AddWeightError? = null,
    val dismissDialog: () -> Unit = {},
) : UiState()
