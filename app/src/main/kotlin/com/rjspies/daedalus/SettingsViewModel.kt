package com.rjspies.daedalus

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PrivacyTip
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class SettingsViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val uiState = savedStateHandle.getStateFlow(
        key = SAVED_STATE_HANDLE_KEY_UI_STATE,
        initialValue = SettingsUiState(legals()),
    )

    private fun legals(): List<SettingItem> = listOf(
        SettingItem(
            titleResourceId = R.string.settings_legal_item_privacy,
            icon = Icons.Rounded.PrivacyTip,
            onClick = {},
        ),
        SettingItem(
            titleResourceId = R.string.settings_legal_item_terms_of_service,
            icon = Icons.Rounded.Description,
            onClick = {},
        ),
        SettingItem(
            titleResourceId = R.string.settings_legal_item_imprint,
            icon = Icons.Rounded.Person,
            onClick = {},
        ),
    )
}
