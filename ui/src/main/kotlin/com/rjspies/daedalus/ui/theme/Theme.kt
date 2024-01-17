package com.rjspies.daedalus.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rjspies.daedalus.ui.banner.Banner
import com.rjspies.daedalus.ui.banner.BannerPresenter
import com.rjspies.daedalus.ui.banner.BannerPresenterImpl
import com.rjspies.daedalus.ui.common.ANIMATION_DURATION_MILLISECONDS

@Composable
public fun DaedalusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DaedalusDarkColors
    } else {
        DaedalusLightColors
    }

    val bannerPresenter = BannerPresenterImpl()

    rememberSystemUiController().setStatusBarColor(colors.background)

    MaterialTheme(
        typography = Typography,
        content = {
            CompositionLocalProvider(
                LocalDaedalusColors provides colors,
                LocalContentColor provides colors.text,
                LocalTextSelectionColors provides TextSelectionColors(
                    handleColor = colors.primary,
                    backgroundColor = colors.text.copy(alpha = .2f),
                ),
                LocalBannerPresenter provides bannerPresenter,
                content = {
                    content()
                    Bannering(bannerPresenter)
                },
            )
        },
    )
}

@Composable
public fun Bannering(bannerPresenter: BannerPresenterImpl) {
    val data by bannerPresenter.data.collectAsState(initial = null)
    AnimatedVisibility(
        visible = data?.visible == true,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION_MILLISECONDS,
                easing = LinearEasing,
            ),
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = ANIMATION_DURATION_MILLISECONDS,
                easing = LinearEasing,
            ),
        ),
        label = "Notification",
    ) {
        Banner()
    }
}

public object DaedalusTheme {
    public val colors: DaedalusColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDaedalusColors.current
}

public val LocalDaedalusColors: ProvidableCompositionLocal<DaedalusColors> = staticCompositionLocalOf {
    error("LocalDaedalusColors are not provided!")
}

public val LocalBannerPresenter: ProvidableCompositionLocal<BannerPresenter> = staticCompositionLocalOf {
    error("LocalBannerPresenter is not provided!")
}
