package com.rjspies.daedalus.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

@Composable
internal fun NavigationBar(
    currentRoute: String?,
    navigate: (destination: TopLevelDestination) -> Unit
) {
    NavigationBar {
        val entries = remember(TopLevelDestination.entries) { TopLevelDestination.entries }
        entries.forEach {
            NavigationBarItem(
                selected = currentRoute == it.route,
                onClick = { navigate(it) },
                label = { Text(stringResource(it.labelResourceId)) },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null,
                    )
                },
            )
        }
    }
}
