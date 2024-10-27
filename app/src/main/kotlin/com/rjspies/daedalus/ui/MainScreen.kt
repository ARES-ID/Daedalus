package com.rjspies.daedalus.ui

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.insertweight.AddWeightDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val navigationController = rememberNavController()
    val viewModel = koinViewModel<MainViewModel>()
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
            val currentBackStackEntry by navigationController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route
            NavigationBar(
                currentRoute = currentRoute,
                navigate = navigationController::navigateToTopLevelDestination,
            )
        },
        content = { NavigationHost(navigationController, it) },
    )
}
