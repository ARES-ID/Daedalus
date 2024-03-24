package com.rjspies.daedalus.ui.common.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.rjspies.daedalus.ui.main.TopLevelDestination

@Composable
internal fun NavigationBar(
    currentRoute: String?,
    navigate: (destination: TopLevelDestination) -> Unit,
) {
    NavigationBar {
        val entries = remember(TopLevelDestination.entries) { TopLevelDestination.entries }
        entries.forEach {
            NavigationBarItem(
                selected = currentRoute == it.route.name,
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
