package me.renespies.daedalus.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import me.renespies.daedalus.R

class DaedalusTypography {
    companion object {
        val headlineLarge: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 32.sp,
                lineHeight = 40.sp,
                fontFamily = DaedalusFonts.Bold,
                fontWeight = FontWeight(400),
                color = DaedalusTheme.colors.text,
            )

        val headlineMedium: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 28.sp,
                lineHeight = 36.sp,
                fontFamily = DaedalusFonts.Bold,
                fontWeight = FontWeight(400),
                color = DaedalusTheme.colors.text,
            )

        val headlineSmall: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontFamily = DaedalusFonts.Bold,
                fontWeight = FontWeight(400),
                color = DaedalusTheme.colors.text,
            )

        val titleLarge: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 22.sp,
                lineHeight = 28.sp,
                fontFamily = DaedalusFonts.Bold,
                fontWeight = FontWeight(400),
                color = DaedalusTheme.colors.text,
            )

        val titleMedium: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = DaedalusFonts.Bold,
                fontWeight = FontWeight(400),
                color = DaedalusTheme.colors.text,
            )

        val titleSmall: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = DaedalusFonts.Bold,
                fontWeight = FontWeight(500),
                color = DaedalusTheme.colors.text,
            )

        val cardSmall: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = DaedalusFonts.Regular,
                fontWeight = FontWeight.Normal,
                color = DaedalusTheme.colors.text,
            )

        val labelLarge: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = DaedalusFonts.Regular,
                fontWeight = FontWeight(500),
                color = DaedalusTheme.colors.text,
            )

        val labelMedium: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 12.sp,
                lineHeight = 15.sp,
                fontFamily = DaedalusFonts.Regular,
                fontWeight = FontWeight(500),
                color = DaedalusTheme.colors.text,
            )

        val labelSmall: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 11.sp,
                lineHeight = 16.sp,
                fontFamily = DaedalusFonts.Regular,
                fontWeight = FontWeight(500),
                color = DaedalusTheme.colors.text,
            )

        val bodyLarge: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = DaedalusFonts.Regular,
                fontWeight = FontWeight(400),
                color = DaedalusTheme.colors.text,
            )

        val bodyMedium: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontFamily = DaedalusFonts.Regular,
                fontWeight = FontWeight(400),
                color = DaedalusTheme.colors.text,
            )

        val bodySmall: TextStyle
            @Composable
            get() = TextStyle(
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontFamily = DaedalusFonts.Regular,
                fontWeight = FontWeight(400),
                color = DaedalusTheme.colors.text,
            )

        @Composable
        fun values() = listOf(
            "Headline large" to headlineLarge,
            "Headline medium" to headlineMedium,
            "Headline small" to headlineSmall,
            "Title large" to titleLarge,
            "Title medium" to titleMedium,
            "Title small" to titleSmall,
            "Label large" to labelLarge,
            "Label medium" to labelMedium,
            "Label small" to labelSmall,
            "Body large" to bodyLarge,
            "Body medium" to bodyMedium,
            "Body small" to bodySmall,
        )
    }
}

object DaedalusFonts {
    val Bold = FontFamily(
        Font(R.font.lato_bold, weight = FontWeight.Bold),
    )

    val Regular = FontFamily(
        Font(R.font.lato_regular),
        Font(R.font.lato_thin, weight = FontWeight.Thin),
    )

    val Debug = FontFamily(
        Font(R.font.islandmoments_regular),
    )
}
