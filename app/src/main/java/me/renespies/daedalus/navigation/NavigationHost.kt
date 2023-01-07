package me.renespies.daedalus.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.renespies.daedalus.addweight.AddWeightScreen
import me.renespies.daedalus.compose.greenEngineeringMenuGestureDetector
import me.renespies.daedalus.greenengineeringmenu.GreenEngineeringMenuScreen
import me.renespies.daedalus.greenengineeringmenu.TypographyGalleryScreen

@Composable
fun NavigationHost(controller: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = controller,
        startDestination = Route.Home.Entry.actual,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .greenEngineeringMenuGestureDetector("navigationHost") {
                if (controller.backQueue.none { it.destination.route == Route.GreenEngineeringMenu.Entry.actual }) {
                    controller.navigate(Route.GreenEngineeringMenu.Entry.actual)
                }
            },
        builder = {
            composable(
                route = Route.GreenEngineeringMenu.Entry.actual,
                content = {
                    GreenEngineeringMenuScreen(
                        onBack = controller::navigateUp,
                        navigateToItem = { controller.navigate(it.actual) }
                    )
                }
            )
            composable(
                route = Route.Home.Entry.actual,
                content = {
                    Button(
                        onClick = { controller.navigate(Route.Home.AddWeight.actual) },
                        content = { Text("add weight", style = MaterialTheme.typography.button) }
                    )
                }
            )
            composable(
                route = Route.GreenEngineeringMenu.Typography.actual,
                content = { TypographyGalleryScreen { controller.navigateUp() } }
            )
            composable(
                route = Route.Home.AddWeight.actual,
                content = { AddWeightScreen(controller::navigateUp) }
            )
        }
    )
}
