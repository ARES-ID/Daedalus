package com.rjspies.daedalus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.AddChart
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rjspies.daedalus.home.HomeScreen
import com.rjspies.daedalus.navigation.Routes
import com.rjspies.daedalus.weight.addweight.AddWeightScreen
import com.rjspies.daedalus.weight.weighthistory.WeightHistoryScreen

@Composable
fun MainScreen() {
    val navigationController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentBackStackRoute = navigationController.currentBackStackEntryAsState().value?.destination?.route
                NavigationBarItem(
                    selected = currentBackStackRoute == Routes.WeightGraph,
                    onClick = { navigationController.navigate(Routes.WeightGraph) },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.AutoGraph,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = "Graph")
                    },
                )
                NavigationBarItem(
                    selected = currentBackStackRoute == Routes.AddWeight,
                    onClick = { navigationController.navigate(Routes.AddWeight) },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.AddChart,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = "Hinzufügen")
                    },
                )
                NavigationBarItem(
                    selected = currentBackStackRoute == Routes.WeightHistory,
                    onClick = { navigationController.navigate(Routes.WeightHistory) },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.List,
                            contentDescription = null,
                        )
                    },
                    label = {
                        Text(text = "Historie")
                    },
                )
            }
        },
        content = {
            Box(Modifier.padding(it)) {
                NavigationHost(navigationController)
            }
        },
    )
}

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
