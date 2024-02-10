package com.rjspies.daedalus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rjspies.daedalus.weight.weightgraph.WeightGraphScreen
import com.rjspies.daedalus.weight.weighthistory.WeightHistoryScreen

@Composable
fun NavigationHost(navigationController: NavHostController) {
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
