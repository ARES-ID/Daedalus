package com.rjspies.daedalus.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.AreaChart
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.common.Route

internal enum class TopLevelDestination(
    val route: Route,
    val icon: ImageVector,
    @StringRes val labelResourceId: Int,
) {
    GraphItem(
        route = Route.WeightGraph,
        icon = Icons.Rounded.AreaChart,
        labelResourceId = R.string.navigation_bar_graph,
    ),
    HistoryItem(
        route = Route.WeightHistory,
        icon = Icons.AutoMirrored.Rounded.List,
        labelResourceId = R.string.navigation_bar_history,
    ),
    SettingsItem(
        route = Route.Settings,
        icon = Icons.Rounded.Settings,
        labelResourceId = R.string.navigation_bar_settings,
    ),
}
