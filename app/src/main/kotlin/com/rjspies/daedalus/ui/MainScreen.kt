package com.rjspies.daedalus.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.utils.rememberDestinationsNavigator
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.insertweight.AddWeightDialog
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val navigationController = rememberNavController()
    val navigator = navigationController.rememberDestinationsNavigator()
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.showDialog) {
        AddWeightDialog {
            viewModel.setShowDialog(false)
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.setShowDialog(true) },
                content = {
                    Icon(
                        painter = painterResource(R.drawable.plus_circle_fill),
                        contentDescription = stringResource(R.string.main_screen_floating_action_button_content_description),
                    )
                },
            )
        },
        bottomBar = {
            val currentDestination = navigationController.appCurrentDestinationAsState().value ?: NavGraphs.mainNavigationGraph.startAppDestination
            NavigationBar(
                currentDestination = currentDestination,
                navigate = {
                    navigator.navigate(it) {
                        restoreState = true
                        launchSingleTop = true
                        popUpTo(NavGraphs.mainNavigationGraph.startRoute) {
                            saveState = true
                        }
                    }
                },
            )
        },
        content = {
            DestinationsNavHost(
                navController = navigationController,
                navGraph = NavGraphs.mainNavigationGraph,
                dependenciesContainerBuilder = {
                    dependency(it)
                    dependency(navigator)
                },
                engine = rememberAnimatedNavHostEngine(rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING),
            )
        },
    )
}
