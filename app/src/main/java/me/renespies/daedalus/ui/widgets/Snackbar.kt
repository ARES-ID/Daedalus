package me.renespies.daedalus.ui.widgets

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.runtime.Composable
import me.renespies.daedalus.ui.theme.DaedalusTheme

@Composable
fun DaedalusSnackbar(data: SnackbarData) {
    Snackbar(
        snackbarData = data,
        containerColor = DaedalusTheme.colors.secondary,
        contentColor = DaedalusTheme.colors.onSecondary,
        actionColor = DaedalusTheme.colors.onSecondary,
        dismissActionContentColor = DaedalusTheme.colors.onSecondary,
        actionContentColor = DaedalusTheme.colors.onSecondary
    )
}
