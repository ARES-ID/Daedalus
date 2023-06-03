package com.rjspies.daedalus.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rjspies.daedalus.R
import com.rjspies.daedalus.compose.greenEngineeringMenuGestureDetector
import com.rjspies.daedalus.greenengineeringmenu.ButtonGalleryScreen
import com.rjspies.daedalus.greenengineeringmenu.GreenEngineeringMenuScreen
import com.rjspies.daedalus.greenengineeringmenu.TypographyGalleryScreen
import com.rjspies.daedalus.home.HomeScreen
import com.rjspies.daedalus.weight.addweight.AddWeightScreen
import com.rjspies.daedalus.weight.weighthistory.WeightHistoryScreen

@Suppress("LongMethod")
@Composable
fun NavigationHost(controller: NavHostController, snackbarHostState: SnackbarHostState) {
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
                content = { _ ->
                    GreenEngineeringMenuScreen(
                        onBack = controller::navigateUp,
                        navigateToItem = { controller.navigate(it) },
                    )
                },
            )
            composable(
                route = Routes.Home,
                content = {
                    HomeScreen(
                        toAddWeight = { controller.navigate(Routes.AddWeight) },
                        toWeightHistory = { controller.navigate(Routes.WeightHistory) },
                    )
                },
            )
            composable(
                route = Routes.Typography,
                content = { TypographyGalleryScreen(controller::navigateUp) },
            )
            composable(
                route = Routes.AddWeight,
                content = {
                    val message = stringResource(R.string.add_weight_add_success_message)
                    AddWeightScreen(
                        onBack = controller::navigateUp,
                        showSnackbar = {
                            snackbarHostState.showSnackbar(
                                message = message,
                                withDismissAction = true,
                            )
                        },
                    )
                },
            )
            composable(
                route = Routes.WeightHistory,
                content = { WeightHistoryScreen(controller::navigateUp) },
            )
            composable(
                route = Routes.Button,
                content = { ButtonGalleryScreen(controller::navigateUp) },
            )
        },
    )
}
