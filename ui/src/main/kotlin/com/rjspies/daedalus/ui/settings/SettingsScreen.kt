package com.rjspies.daedalus.ui.settings

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.rjspies.daedalus.ui.R
import com.rjspies.daedalus.ui.common.HorizontalSpacerM
import com.rjspies.daedalus.ui.common.VerticalSpacerM
import com.rjspies.daedalus.ui.common.horizontalSpacingM
import com.rjspies.daedalus.ui.common.theme.Spacings
import org.koin.androidx.compose.koinViewModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Composable
internal fun SettingsScreen(
    innerPadding: PaddingValues,
    viewModel: SettingsViewModel = koinViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        val uiState by viewModel.uiState.collectAsState()
        val legals = uiState.legals
        Text(
            text = stringResource(R.string.settings_title),
            modifier = Modifier
                .fillMaxWidth()
                .horizontalSpacingM(),
            style = MaterialTheme.typography.headlineLarge,
        )
        VerticalSpacerM()
        LazyColumn(verticalArrangement = Arrangement.spacedBy(Spacings.M)) {
            items(
                items = legals,
                key = { it },
                itemContent = {
                    Card(
                        onClick = it.onClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalSpacingM(),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Spacings.M),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(stringResource(it.titleResourceId))
                            HorizontalSpacerM()
                            Icon(
                                imageVector = it.icon,
                                contentDescription = null,
                            )
                        }
                    }
                },
            )
        }
    }
}

@Parcelize
internal data class SettingItem(
    @StringRes val titleResourceId: Int,
    val icon: @RawValue ImageVector,
    val onClick: () -> Unit,
) : Parcelable
