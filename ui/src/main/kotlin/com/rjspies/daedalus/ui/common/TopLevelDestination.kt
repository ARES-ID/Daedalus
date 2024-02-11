package com.rjspies.daedalus.ui.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.AreaChart
import androidx.compose.ui.graphics.vector.ImageVector
import com.rjspies.daedalus.ui.R

internal enum class TopLevelDestination(
    val route: String,
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
}
