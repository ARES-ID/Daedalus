package me.renespies.daedalus.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.renespies.daedalus.compose.greenEngineeringMenuGestureDetector
import me.renespies.daedalus.greenengineeringmenu.ButtonGalleryScreen
import me.renespies.daedalus.greenengineeringmenu.GreenEngineeringMenuScreen
import me.renespies.daedalus.greenengineeringmenu.TypographyGalleryScreen
import me.renespies.daedalus.home.HomeScreen
import me.renespies.daedalus.weight.addweight.AddWeightScreen
import me.renespies.daedalus.weight.weighthistory.WeightHistoryScreen

@Composable
fun NavigationHost(controller: NavHostController) {
    NavHost(
        navController = controller,
        startDestination = Routes.Home,
        modifier = Modifier
            .fillMaxSize()
            .greenEngineeringMenuGestureDetector("navigationHost") {
                if (controller.backQueue.none { it.destination.route == Routes.GreenEngineeringMenu }) {
                    controller.navigate(Routes.GreenEngineeringMenu)
                }
            },
        builder = {
            composable(
                route = Routes.GreenEngineeringMenu,
                content = {
                    GreenEngineeringMenuScreen(
                        onBack = controller::navigateUp,
                        navigateToItem = { controller.navigate(it) }
                    )
                }
            )
            composable(
                route = Routes.Home,
                content = {
                    HomeScreen(
                        toAddWeight = { controller.navigate(Routes.AddWeight) },
                        toWeightHistory = { controller.navigate(Routes.WeightHistory) }
                    )
                }
            )
            composable(
                route = Routes.Typography,
                content = { TypographyGalleryScreen(controller::navigateUp) }
            )
            composable(
                route = Routes.AddWeight,
                content = { AddWeightScreen(controller::navigateUp) }
            )
            composable(
                route = Routes.WeightHistory,
                content = { WeightHistoryScreen(controller::navigateUp) }
            )
            composable(
                route = Routes.Button,
                content = { ButtonGalleryScreen(controller::navigateUp) }
            )
        }
    )
}
