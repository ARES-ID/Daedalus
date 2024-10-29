package com.rjspies.daedalus.ui

import com.rjspies.daedalus.ui.common.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainUiState(
    val showDialog: Boolean = false,
) : UiState
