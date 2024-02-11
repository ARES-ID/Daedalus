package com.rjspies.daedalus.ui.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rjspies.daedalus.ui.WeightGraphScreen
import com.rjspies.daedalus.ui.WeightHistoryScreen

@Composable
internal fun NavigationHost(navigationController: NavHostController) {
    NavHost(
        navController = navigationController,
        startDestination = Route.WeightGraph,
    ) {
        composable(Route.WeightGraph) {
            WeightGraphScreen()
        }
        composable(Route.WeightHistory) {
            WeightHistoryScreen()
        }
    }
}
