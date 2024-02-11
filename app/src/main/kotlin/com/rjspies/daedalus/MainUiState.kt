package com.rjspies.daedalus

import kotlinx.parcelize.Parcelize

@Parcelize
data class MainUiState(
    val showDialog: Boolean = false,
) : UiState
