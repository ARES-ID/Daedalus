package com.rjspies.daedalus

import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SettingsUiState(
    val legals: List<SettingItem> = emptyList(),
) : UiState
