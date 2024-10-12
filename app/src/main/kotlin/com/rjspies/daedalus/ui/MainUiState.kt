package com.rjspies.daedalus.ui

import kotlinx.parcelize.Parcelize

@Parcelize
internal data class MainUiState(
    val showDialog: Boolean = false,
) : UiState
