package com.rjspies.daedalus.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

internal fun NavController.navigateToTopLevelDestination(destination: TopLevelDestination) {
    navigate(destination.route.name) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
