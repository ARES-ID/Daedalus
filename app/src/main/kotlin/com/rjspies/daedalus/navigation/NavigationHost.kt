package com.rjspies.daedalus.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rjspies.daedalus.R
import com.rjspies.daedalus.compose.greenEngineeringMenuGestureDetector
import com.rjspies.daedalus.greenengineeringmenu.ButtonGalleryScreen
import com.rjspies.daedalus.greenengineeringmenu.GreenEngineeringMenuScreen
import com.rjspies.daedalus.greenengineeringmenu.TypographyGalleryScreen
import com.rjspies.daedalus.home.HomeScreen
import com.rjspies.daedalus.ui.common.animatedComposable
import com.rjspies.daedalus.weight.addweight.AddWeightScreen
import com.rjspies.daedalus.weight.weighthistory.WeightHistoryScreen

@Suppress("LongMethod")
@Composable
fun NavigationHost(snackbarHostState: SnackbarHostState) {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = Routes.Home,
        modifier = Modifier
            .fillMaxSize()
            .greenEngineeringMenuGestureDetector("navigationHost") {
                controller.navigate(Routes.GreenEngineeringMenu) {
                    launchSingleTop = true
                }
            },
        builder = {
            animatedComposable(
                route = Routes.GreenEngineeringMenu,
                content = { _ ->
                    GreenEngineeringMenuScreen(
                        onBack = controller::navigateUp,
                        navigateToItem = { controller.navigate(it) },
                    )
                },
            )
            animatedComposable(
                route = Routes.Home,
                content = {
                    HomeScreen(
                        toAddWeight = { controller.navigate(Routes.AddWeight) },
                        toWeightHistory = { controller.navigate(Routes.WeightHistory) },
                    )
                },
            )
            animatedComposable(
                route = Routes.Typography,
                content = { TypographyGalleryScreen(controller::navigateUp) },
            )
            animatedComposable(
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
            animatedComposable(
                route = Routes.WeightHistory,
                content = { WeightHistoryScreen(controller::navigateUp) },
            )
            animatedComposable(
                route = Routes.Button,
                content = { ButtonGalleryScreen(controller::navigateUp) },
            )
        },
    )
}
