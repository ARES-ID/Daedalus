package com.rjspies.daedalus

import kotlinx.parcelize.Parcelize

@Parcelize
internal data class InsertWeightUiState(
    val isLoading: Boolean = false,
    val error: com.rjspies.daedalus.AddWeightError? = null,
    val dismissDialog: () -> Unit = {},
) : UiState
