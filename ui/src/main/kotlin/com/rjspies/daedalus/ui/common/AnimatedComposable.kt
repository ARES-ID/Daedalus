package com.rjspies.daedalus.ui.common

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

public const val ANIMATION_DURATION_MILLISECONDS: Int = 200

public fun NavGraphBuilder.animatedComposable(
    route: String,
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable(
        route = route,
        enterTransition = {
            slideIntoContainer(
                towards = SlideDirection.Start,
                animationSpec = tween(ANIMATION_DURATION_MILLISECONDS),
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = SlideDirection.Start,
                animationSpec = tween(ANIMATION_DURATION_MILLISECONDS),
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = SlideDirection.End,
                animationSpec = tween(ANIMATION_DURATION_MILLISECONDS),
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = SlideDirection.End,
                animationSpec = tween(ANIMATION_DURATION_MILLISECONDS),
            )
        },
        content = content,
    )
}
