package me.renespies.daedalus.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import me.renespies.daedalus.compose.greenEngineeringMenuGestureDetector
import me.renespies.daedalus.greenengineeringmenu.GreenEngineeringMenuScreen
import me.renespies.daedalus.greenengineeringmenu.TypographyGalleryScreen

@Composable
fun NavigationHost(controller: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = controller,
        startDestination = Route.Home.actual,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .greenEngineeringMenuGestureDetector("navigationHost") {
                controller.navigate(Route.GreenEngineeringMenu.Entry.actual) {
                    launchSingleTop = true
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
                route = Route.Home.actual,
                content = {
                    Text(text = "Home", style = MaterialTheme.typography.h1)
                }
            )
            composable(
                route = Route.GreenEngineeringMenu.Typography.actual,
                content = { TypographyGalleryScreen { controller.navigateUp() } }
            )
        }
    )
}
