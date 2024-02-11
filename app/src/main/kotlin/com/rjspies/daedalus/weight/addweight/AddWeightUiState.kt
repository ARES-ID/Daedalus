package com.rjspies.daedalus.weight.addweight

import com.rjspies.daedalus.AddWeightError
import com.rjspies.daedalus.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddWeightUiState(
    val isLoading: Boolean = false,
    val error: AddWeightError? = null,
    val dismissDialog: () -> Unit = {},
) : UiState
