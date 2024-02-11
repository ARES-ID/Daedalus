package com.rjspies.daedalus.ui.addweight

import com.rjspies.daedalus.common.AddWeightError
import com.rjspies.daedalus.ui.common.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class AddWeightUiState(
    val isLoading: Boolean = false,
    val error: AddWeightError? = null,
    val dismissDialog: () -> Unit = {},
) : UiState
