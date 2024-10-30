package com.rjspies.daedalus.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rjspies.daedalus.ui.common.SAVED_STATE_HANDLE_KEY_UI_STATE

class MainViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val uiState = savedStateHandle.getStateFlow(
        key = SAVED_STATE_HANDLE_KEY_UI_STATE,
        initialValue = MainUiState(),
    )

    fun setShowDialog(showDialog: Boolean) {
        savedStateHandle[SAVED_STATE_HANDLE_KEY_UI_STATE] = uiState.value.copy(showDialog = showDialog)
    }
}
