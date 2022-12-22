package me.renespies.daedalus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun DaedalusTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DaedalusDarkColors
    } else {
        DaedalusLightColors
    }

    MaterialTheme(
        colors = debugColors(darkTheme),
        typography = Typography(DaedalusFonts.Debug),
        shapes = Shapes,
        content = {
            CompositionLocalProvider(
                LocalDaedalusColors provides colors,
                LocalTextStyle provides TextStyle(color = Color(0xFF00FF00)),
                content = content,
            )
        }
    )
}

object DaedalusTheme {
    val colors: DaedalusColors
        @ReadOnlyComposable
        @Composable
        get() = LocalDaedalusColors.current
}

val LocalDaedalusColors = staticCompositionLocalOf<DaedalusColors> {
    error("No LocalDaedalusColors provided")
}

private fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color(0xFFFF00FF),
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme,
)
