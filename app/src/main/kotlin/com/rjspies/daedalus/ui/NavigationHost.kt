package com.rjspies.daedalus.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rjspies.daedalus.ui.diagram.WeightDiagramScreen
import com.rjspies.daedalus.ui.history.WeightHistoryScreen
import com.rjspies.daedalus.ui.common.Route
import com.rjspies.daedalus.ui.settings.SettingsScreen

@Composable
internal fun NavigationHost(
    navigationController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navigationController,
        startDestination = Route.WeightGraph.name,
    ) {
        composable(Route.WeightGraph.name) {
            WeightDiagramScreen(innerPadding)
        }
        composable(Route.WeightHistory.name) {
            WeightHistoryScreen(innerPadding)
        }
        composable(Route.Settings.name) {
            SettingsScreen(innerPadding)
        }
    }
}
