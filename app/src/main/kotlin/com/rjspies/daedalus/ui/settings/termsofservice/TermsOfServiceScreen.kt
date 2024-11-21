package com.rjspies.daedalus.ui.settings.termsofservice

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.common.TopAppBar

@Destination
@Composable
fun TermsOfServiceScreen(navigator: DestinationsNavigator) {
    TopAppBar(
        title = stringResource(R.string.settings_legal_item_terms_of_service),
        navigator = navigator,
    ) {

    }
}
