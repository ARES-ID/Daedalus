package com.rjspies.daedalus

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rjspies.daedalus.weight.addweight.UiState
import org.koin.android.annotation.KoinViewModel

private const val HANDLE_KEY_UI_STATE = "HANDLE_KEY_UI_STATE"

@KoinViewModel
class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val uiState = savedStateHandle.getStateFlow(
        key = HANDLE_KEY_UI_STATE,
        initialValue = MainUiState(),
    )

    fun setShowDialog(showDialog: Boolean) {
        savedStateHandle[HANDLE_KEY_UI_STATE] = uiState.value.copy(showDialog = showDialog)
    }
}

data class MainUiState(
    val showDialog: Boolean = false,
) : UiState()
