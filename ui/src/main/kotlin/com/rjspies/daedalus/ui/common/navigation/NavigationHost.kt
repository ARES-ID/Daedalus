package com.rjspies.daedalus.ui.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rjspies.daedalus.ui.common.Route
import com.rjspies.daedalus.ui.weightgraph.WeightGraphScreen
import com.rjspies.daedalus.ui.weighthistory.WeightHistoryScreen

@Composable
internal fun NavigationHost(
    navigationController: NavHostController,
    innerPadding: PaddingValues
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
    }
}
