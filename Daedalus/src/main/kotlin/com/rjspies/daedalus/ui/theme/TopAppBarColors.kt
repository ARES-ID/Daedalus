package com.rjspies.daedalus.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun daedalusTopAppBarColors(): TopAppBarColors {
    return TopAppBarDefaults.topAppBarColors(
        containerColor = DaedalusTheme.colors.background,
        titleContentColor = DaedalusTheme.colors.text,
        navigationIconContentColor = DaedalusTheme.colors.text,
        actionIconContentColor = DaedalusTheme.colors.text,
    )
}
