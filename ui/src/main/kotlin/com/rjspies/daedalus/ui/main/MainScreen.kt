package com.rjspies.daedalus.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddChart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rjspies.daedalus.ui.addweight.AddWeightDialog
import com.rjspies.daedalus.ui.common.navigation.NavigationBar
import com.rjspies.daedalus.ui.common.navigation.NavigationHost
import com.rjspies.daedalus.ui.common.navigation.navigateToTopLevelDestination
import org.koin.androidx.compose.koinViewModel

@Composable
public fun MainScreen() {
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
                        imageVector = Icons.Rounded.AddChart,
                        contentDescription = null,
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
        content = {
            Box(Modifier.padding(it)) {
                NavigationHost(navigationController)
            }
        },
    )
}
