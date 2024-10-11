package com.rjspies.daedalus

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            WeightGraphScreen(innerPadding)
        }
        composable(Route.WeightHistory.name) {
            WeightHistoryScreen(innerPadding)
        }
        composable(Route.Settings.name) {
            SettingsScreen(innerPadding)
        }
    }
}
