package com.rjspies.daedalus.ui.insertweight

import com.rjspies.daedalus.common.AddWeightError
import com.rjspies.daedalus.ui.common.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class InsertWeightUiState(
    val isLoading: Boolean = false,
    val error: AddWeightError? = null,
    val dismissDialog: () -> Unit = {},
) : UiState
