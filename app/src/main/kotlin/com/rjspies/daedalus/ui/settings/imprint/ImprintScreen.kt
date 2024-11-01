package com.rjspies.daedalus.ui.settings.imprint

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.common.TopAppBar

@Destination
@Composable
fun ImprintScreen(navigator: DestinationsNavigator) {
    TopAppBar(
        title = stringResource(R.string.settings_legal_item_imprint),
        navigator = navigator,
    ) {

    }
}
