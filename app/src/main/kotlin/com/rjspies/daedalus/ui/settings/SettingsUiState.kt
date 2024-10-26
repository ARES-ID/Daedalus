package com.rjspies.daedalus.ui.settings

import com.rjspies.daedalus.ui.common.UiState
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SettingsUiState(
    val legals: List<SettingItem> = emptyList(),
) : UiState
