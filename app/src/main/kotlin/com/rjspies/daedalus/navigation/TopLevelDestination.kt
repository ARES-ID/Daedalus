package com.rjspies.daedalus.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.AreaChart
import androidx.compose.ui.graphics.vector.ImageVector
import com.rjspies.daedalus.R

enum class TopLevelDestination(
    val route: String,
    val icon: ImageVector,
    @StringRes val labelResourceId: Int,
) {
    GraphItem(
        route = Route.WeightGraph,
        icon = Icons.Rounded.AreaChart,
        labelResourceId = R.string.green_engineering_menu_item_typography_title,
    ),
    HistoryItem(
        route = Route.WeightHistory,
        icon = Icons.AutoMirrored.Rounded.List,
        labelResourceId = R.string.weight_history_item_note,
    ),
}
