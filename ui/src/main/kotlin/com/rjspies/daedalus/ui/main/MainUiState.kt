package com.rjspies.daedalus.ui.main

import com.rjspies.daedalus.ui.common.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class MainUiState(
    val showDialog: Boolean = false,
) : UiState
