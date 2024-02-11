package com.rjspies.daedalus.ui.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rjspies.daedalus.ui.common.Route
import com.rjspies.daedalus.ui.weightgraph.WeightGraphScreen
import com.rjspies.daedalus.ui.weighthistory.WeightHistoryScreen

@Composable
internal fun NavigationHost(navigationController: NavHostController) {
    NavHost(
        navController = navigationController,
        startDestination = Route.WeightGraph.name,
    ) {
        composable(Route.WeightGraph.name) {
            WeightGraphScreen()
        }
        composable(Route.WeightHistory.name) {
            WeightHistoryScreen()
        }
    }
}
