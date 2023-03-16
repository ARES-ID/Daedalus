package me.renespies.daedalus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DaedalusTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DaedalusDarkColors
    } else {
        DaedalusLightColors
    }

    rememberSystemUiController().setStatusBarColor(colors.background)

    MaterialTheme(
        typography = Typography,
        shapes = Shapes,
        content = {
            CompositionLocalProvider(
                LocalDaedalusColors provides colors,
                content = content
            )
        }
    )
}

object DaedalusTheme {
    val colors: DaedalusColors
        @Composable
        @ReadOnlyComposable
        get() = LocalDaedalusColors.current
}

val LocalDaedalusColors: ProvidableCompositionLocal<DaedalusColors> = staticCompositionLocalOf { error("LocalDaedalusColors are not provided!") }
