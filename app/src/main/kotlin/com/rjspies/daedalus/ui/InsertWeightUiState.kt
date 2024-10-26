package com.rjspies.daedalus.ui

import com.rjspies.daedalus.AddWeightError
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class InsertWeightUiState(
    val isLoading: Boolean = false,
    val error: AddWeightError? = null,
    val dismissDialog: () -> Unit = {},
) : UiState
