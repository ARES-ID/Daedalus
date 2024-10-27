package com.rjspies.daedalus.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.common.Route

internal enum class TopLevelDestination(
    val route: Route,
    @DrawableRes val iconResourceId: Int,
    @StringRes val labelResourceId: Int,
) {
    GraphItem(
        route = Route.WeightGraph,
        iconResourceId = R.drawable.chart_line_fill,
        labelResourceId = R.string.navigation_bar_graph,
    ),
    HistoryItem(
        route = Route.WeightHistory,
        iconResourceId = R.drawable.list_bullets_fill,
        labelResourceId = R.string.navigation_bar_history,
    ),
    SettingsItem(
        route = Route.Settings,
        iconResourceId = R.drawable.gear_fine_fill,
        labelResourceId = R.string.navigation_bar_settings,
    ),
}
