package com.rjspies.daedalus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

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
                content = content,
            )
        },
    )
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
