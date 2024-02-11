package com.rjspies.daedalus.ui.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.rjspies.daedalus.ui.main.TopLevelDestination

internal fun NavController.navigateToTopLevelDestination(destination: TopLevelDestination) {
    navigate(destination.route.name) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
