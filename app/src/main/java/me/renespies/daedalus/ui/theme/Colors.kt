package me.renespies.daedalus.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private interface DaedalusColors {
    val primary: Color
    val onPrimary: Color
    val primaryContainer: Color
    val onPrimaryContainer: Color
    val secondary: Color
    val onSecondary: Color
    val secondaryContainer: Color
    val onSecondaryContainer: Color
    val tertiary: Color
    val onTertiary: Color
    val tertiaryContainer: Color
    val onTertiaryContainer: Color
    val error: Color
    val errorContainer: Color
    val onError: Color
    val onErrorContainer: Color
    val background: Color
    val onBackground: Color
    val surface: Color
    val onSurface: Color
    val surfaceVariant: Color
    val onSurfaceVariant: Color
    val outline: Color
    val inverseOnSurface: Color
    val inverseSurface: Color
    val inversePrimary: Color
    val surfaceTint: Color
    val outlineVariant: Color
    val scrim: Color
}

private object DaedalusLightColors : DaedalusColors {
    override val primary: Color
        get() = Color(0xFF006C4D)
    override val onPrimary: Color
        get() = Color(0xFFFFFFFF)
    override val primaryContainer: Color
        get() = Color(0xFF88F8C8)
    override val onPrimaryContainer: Color
        get() = Color(0xFF002115)
    override val secondary: Color
        get() = Color(0xFF4C6358)
    override val onSecondary: Color
        get() = Color(0xFFFFFFFF)
    override val secondaryContainer: Color
        get() = Color(0xFFCFE9DA)
    override val onSecondaryContainer: Color
        get() = Color(0xFF092016)
    override val tertiary: Color
        get() = Color(0xFF3E6374)
    override val onTertiary: Color
        get() = Color(0xFFFFFFFF)
    override val tertiaryContainer: Color
        get() = Color(0xFFC1E8FC)
    override val onTertiaryContainer: Color
        get() = Color(0xFF001F29)
    override val error: Color
        get() = Color(0xFFBA1A1A)
    override val errorContainer: Color
        get() = Color(0xFFFFDAD6)
    override val onError: Color
        get() = Color(0xFFFFFFFF)
    override val onErrorContainer: Color
        get() = Color(0xFF410002)
    override val background: Color
        get() = Color(0xFFFBFDF9)
    override val onBackground: Color
        get() = Color(0xFF191C1A)
    override val surface: Color
        get() = Color(0xFFFBFDF9)
    override val onSurface: Color
        get() = Color(0xFF191C1A)
    override val surfaceVariant: Color
        get() = Color(0xFFDBE5DD)
    override val onSurfaceVariant: Color
        get() = Color(0xFF404944)
    override val outline: Color
        get() = Color(0xFF707973)
    override val inverseOnSurface: Color
        get() = Color(0xFFEFF1ED)
    override val inverseSurface: Color
        get() = Color(0xFF2E312F)
    override val inversePrimary: Color
        get() = Color(0xFF6BDBAD)
    override val surfaceTint: Color
        get() = Color(0xFF000000)
    override val outlineVariant: Color
        get() = Color(0xFF006C4D)
    override val scrim: Color
        get() = Color(0xFFBFC9C2)
}

private object DaedalusDarkColors : DaedalusColors {
    override val primary: Color
        get() = Color(0xFF6BDBAD)
    override val onPrimary: Color
        get() = Color(0xFF003826)
    override val primaryContainer: Color
        get() = Color(0xFF005139)
    override val onPrimaryContainer: Color
        get() = Color(0xFF88F8C8)
    override val secondary: Color
        get() = Color(0xFFB3CCBE)
    override val onSecondary: Color
        get() = Color(0xFF1F352B)
    override val secondaryContainer: Color
        get() = Color(0xFF354B41)
    override val onSecondaryContainer: Color
        get() = Color(0xFFCFE9DA)
    override val tertiary: Color
        get() = Color(0xFFA6CCDF)
    override val onTertiary: Color
        get() = Color(0xFF083544)
    override val tertiaryContainer: Color
        get() = Color(0xFF254B5B)
    override val onTertiaryContainer: Color
        get() = Color(0xFFC1E8FC)
    override val error: Color
        get() = Color(0xFFFFB4AB)
    override val errorContainer: Color
        get() = Color(0xFF93000A)
    override val onError: Color
        get() = Color(0xFF690005)
    override val onErrorContainer: Color
        get() = Color(0xFFFFDAD6)
    override val background: Color
        get() = Color(0xFF191C1A)
    override val onBackground: Color
        get() = Color(0xFFE1E3DF)
    override val surface: Color
        get() = Color(0xFF191C1A)
    override val onSurface: Color
        get() = Color(0xFFE1E3DF)
    override val surfaceVariant: Color
        get() = Color(0xFF404944)
    override val onSurfaceVariant: Color
        get() = Color(0xFFBFC9C2)
    override val outline: Color
        get() = Color(0xFF8A938D)
    override val inverseOnSurface: Color
        get() = Color(0xFF191C1A)
    override val inverseSurface: Color
        get() = Color(0xFFE1E3DF)
    override val inversePrimary: Color
        get() = Color(0xFF006C4D)
    override val surfaceTint: Color
        get() = Color(0xFF000000)
    override val outlineVariant: Color
        get() = Color(0xFF6BDBAD)
    override val scrim: Color
        get() = Color(0xFF404944)
}

val LightColors = lightColorScheme(
    primary = DaedalusLightColors.primary,
    onPrimary = DaedalusLightColors.onPrimary,
    primaryContainer = DaedalusLightColors.primaryContainer,
    onPrimaryContainer = DaedalusLightColors.onPrimaryContainer,
    secondary = DaedalusLightColors.secondary,
    onSecondary = DaedalusLightColors.onSecondary,
    secondaryContainer = DaedalusLightColors.secondaryContainer,
    onSecondaryContainer = DaedalusLightColors.onSecondaryContainer,
    tertiary = DaedalusLightColors.tertiary,
    onTertiary = DaedalusLightColors.onTertiary,
    tertiaryContainer = DaedalusLightColors.tertiaryContainer,
    onTertiaryContainer = DaedalusLightColors.onTertiaryContainer,
    error = DaedalusLightColors.error,
    errorContainer = DaedalusLightColors.errorContainer,
    onError = DaedalusLightColors.onError,
    onErrorContainer = DaedalusLightColors.onErrorContainer,
    background = DaedalusLightColors.background,
    onBackground = DaedalusLightColors.onBackground,
    surface = DaedalusLightColors.surface,
    onSurface = DaedalusLightColors.onSurface,
    surfaceVariant = DaedalusLightColors.surfaceVariant,
    onSurfaceVariant = DaedalusLightColors.onSurfaceVariant,
    outline = DaedalusLightColors.outline,
    inverseOnSurface = DaedalusLightColors.inverseOnSurface,
    inverseSurface = DaedalusLightColors.inverseSurface,
    inversePrimary = DaedalusLightColors.inversePrimary,
    surfaceTint = DaedalusLightColors.surfaceTint,
    outlineVariant = DaedalusLightColors.outlineVariant,
    scrim = DaedalusLightColors.scrim,
)

val DarkColors = darkColorScheme(
    primary = DaedalusDarkColors.primary,
    onPrimary = DaedalusDarkColors.onPrimary,
    primaryContainer = DaedalusDarkColors.primaryContainer,
    onPrimaryContainer = DaedalusDarkColors.onPrimaryContainer,
    secondary = DaedalusDarkColors.secondary,
    onSecondary = DaedalusDarkColors.onSecondary,
    secondaryContainer = DaedalusDarkColors.secondaryContainer,
    onSecondaryContainer = DaedalusDarkColors.onSecondaryContainer,
    tertiary = DaedalusDarkColors.tertiary,
    onTertiary = DaedalusDarkColors.onTertiary,
    tertiaryContainer = DaedalusDarkColors.tertiaryContainer,
    onTertiaryContainer = DaedalusDarkColors.onTertiaryContainer,
    error = DaedalusDarkColors.error,
    errorContainer = DaedalusDarkColors.errorContainer,
    onError = DaedalusDarkColors.onError,
    onErrorContainer = DaedalusDarkColors.onErrorContainer,
    background = DaedalusDarkColors.background,
    onBackground = DaedalusDarkColors.onBackground,
    surface = DaedalusDarkColors.surface,
    onSurface = DaedalusDarkColors.onSurface,
    surfaceVariant = DaedalusDarkColors.surfaceVariant,
    onSurfaceVariant = DaedalusDarkColors.onSurfaceVariant,
    outline = DaedalusDarkColors.outline,
    inverseOnSurface = DaedalusDarkColors.inverseOnSurface,
    inverseSurface = DaedalusDarkColors.inverseSurface,
    inversePrimary = DaedalusDarkColors.inversePrimary,
    surfaceTint = DaedalusDarkColors.surfaceTint,
    outlineVariant = DaedalusDarkColors.outlineVariant,
    scrim = DaedalusDarkColors.scrim,
)
