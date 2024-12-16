package com.rjspies.daedalus.ui.settings

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.common.HorizontalSpacerM
import com.rjspies.daedalus.ui.common.Spacings
import com.rjspies.daedalus.ui.common.VerticalSpacerM
import com.rjspies.daedalus.ui.common.horizontalSpacingM
import org.koin.androidx.compose.koinViewModel
import kotlinx.parcelize.Parcelize

@Destination
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    scaffoldPadding: PaddingValues,
    navigator: DestinationsNavigator,
) {
    val uiState by viewModel.uiState.collectAsState()
    val legals = uiState.legals
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Spacings.M),
        contentPadding = scaffoldPadding,
    ) {
        item {
            Text(
                text = stringResource(R.string.settings_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalSpacingM(),
                style = MaterialTheme.typography.headlineLarge,
            )
            VerticalSpacerM()
        }

        items(
            items = legals,
            key = { it.itemIdResourceId },
            itemContent = {
                Card(
                    onClick = { navigator.navigate(it.destination()) },
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
                            painter = painterResource(it.iconResourceId),
                            contentDescription = null,
                        )
                    }
                }
            },
        )
    }
}

@Parcelize
data class SettingItem(
    @IdRes val itemIdResourceId: Int,
    @StringRes val titleResourceId: Int,
    @DrawableRes val iconResourceId: Int,
    val destination: () -> DirectionDestinationSpec,
) : Parcelable
