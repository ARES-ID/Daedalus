package com.rjspies.daedalus.ui

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.rjspies.daedalus.R

@Composable
public fun DaedalusTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (darkTheme) {
            dynamicDarkColorScheme(context)
        } else {
            dynamicLightColorScheme(context)
        }
    } else {
        if (darkTheme) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content,
    )
}

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

private val headlineFontName = GoogleFont("Zilla Slab")
private val headlineFontFamily = FontFamily(
    Font(
        googleFont = headlineFontName,
        fontProvider = provider,
    ),
)

private val typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = headlineFontFamily,
        fontWeight = FontWeight(400),
        fontSize = 32.sp,
        letterSpacing = 0.sp,
        lineHeight = 40.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = headlineFontFamily,
        fontWeight = FontWeight(400),
        fontSize = 28.sp,
        letterSpacing = 0.sp,
        lineHeight = 36.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = headlineFontFamily,
        fontWeight = FontWeight(400),
        fontSize = 24.sp,
        letterSpacing = 0.sp,
        lineHeight = 32.sp,
    ),
)
