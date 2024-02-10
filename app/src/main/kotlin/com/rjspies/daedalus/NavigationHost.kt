package com.rjspies.daedalus

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rjspies.daedalus.home.HomeScreen
import com.rjspies.daedalus.navigation.Routes
import com.rjspies.daedalus.weight.addweight.AddWeightScreen
import com.rjspies.daedalus.weight.weighthistory.WeightHistoryScreen

@Composable
fun NavigationHost(navigationController: NavHostController) {
    NavHost(
        navController = navigationController,
        startDestination = Routes.WeightGraph,
    ) {
        composable(Routes.WeightGraph) {
            HomeScreen(toAddWeight = { /*TODO*/ }) {

            }
        }
        composable(Routes.AddWeight) {
            AddWeightScreen(onBack = { /*TODO*/ }, showSnackbar = {})
        }
        composable(Routes.WeightHistory) {
            WeightHistoryScreen {

            }
        }
    }
}
