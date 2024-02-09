package com.rjspies.daedalus.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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
        startDestination = Routes.WeightGraph,
        modifier = Modifier.fillMaxSize(),
        builder = {
            animatedComposable(
                route = Routes.WeightGraph,
                content = {
                    HomeScreen(
                        toAddWeight = { controller.navigate(Routes.AddWeight) },
                        toWeightHistory = { controller.navigate(Routes.WeightHistory) },
                    )
                },
            )
            animatedComposable(
                route = Routes.AddWeight,
                content = {
                    AddWeightScreen(
                        onBack = controller::navigateUp,
                        showSnackbar = {
                            snackbarHostState.showSnackbar(
                                message = it,
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
        },
    )
}
