package com.rjspies.daedalus

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.test.filters.SmallTest
import com.rjspies.daedalus.ui.theme.Typography
import org.junit.Assert.assertEquals
import org.junit.Test

class TypographyTest {
    private val medium = FontFamily(Font(R.font.poppins_medium, weight = FontWeight.Medium))
    private val regular = FontFamily(Font(R.font.poppins_regular, weight = FontWeight.Normal))

    private val displayLarge by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 64.sp,
            fontSize = 57.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val displayMedium by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 52.sp,
            fontSize = 45.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val displaySmall by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 44.sp,
            fontSize = 36.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val headlineLarge by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 40.sp,
            fontSize = 32.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val headlineMedium by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 36.sp,
            fontSize = 28.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val headlineSmall by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 32.sp,
            fontSize = 24.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val titleLarge by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 28.sp,
            fontSize = 22.sp,
            letterSpacing = 0.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val titleMedium by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 24.sp,
            fontSize = 16.sp,
            letterSpacing = 0.15.sp,
            fontWeight = FontWeight(500)
        )
    }

    private val titleSmall by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
            fontWeight = FontWeight(500)
        )
    }

    private val labelLarge by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            letterSpacing = 0.1.sp,
            fontWeight = FontWeight(500)
        )
    }

    private val labelMedium by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 16.sp,
            fontSize = 12.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight(500)
        )
    }

    private val labelSmall by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 16.sp,
            fontSize = 11.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight(500)
        )
    }

    private val bodyLarge by lazy {
        TextStyle(
            fontFamily = medium,
            lineHeight = 24.sp,
            fontSize = 16.sp,
            letterSpacing = 0.5.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val bodyMedium by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 20.sp,
            fontSize = 14.sp,
            letterSpacing = 0.25.sp,
            fontWeight = FontWeight(400)
        )
    }

    private val bodySmall by lazy {
        TextStyle(
            fontFamily = regular,
            lineHeight = 16.sp,
            fontSize = 12.sp,
            letterSpacing = 0.4.sp,
            fontWeight = FontWeight(400)
        )
    }

    @Test
    @SmallTest
    fun displayLarge() {
        assertEquals(displayLarge, Typography.displayLarge)
    }

    @Test
    @SmallTest
    fun displayMedium() {
        assertEquals(displayMedium, Typography.displayMedium)
    }

    @Test
    @SmallTest
    fun displaySmall() {
        assertEquals(displaySmall, Typography.displaySmall)
    }

    @Test
    @SmallTest
    fun headlineLarge() {
        assertEquals(headlineLarge, Typography.headlineLarge)
    }

    @Test
    @SmallTest
    fun headlineMedium() {
        assertEquals(headlineMedium, Typography.headlineMedium)
    }

    @Test
    @SmallTest
    fun headlineSmall() {
        assertEquals(headlineSmall, Typography.headlineSmall)
    }

    @Test
    @SmallTest
    fun titleLarge() {
        assertEquals(titleLarge, Typography.titleLarge)
    }

    @Test
    @SmallTest
    fun titleMedium() {
        assertEquals(titleMedium, Typography.titleMedium)
    }

    @Test
    @SmallTest
    fun titleSmall() {
        assertEquals(titleSmall, Typography.titleSmall)
    }

    @Test
    @SmallTest
    fun labelLarge() {
        assertEquals(labelLarge, Typography.labelLarge)
    }

    @Test
    @SmallTest
    fun labelMedium() {
        assertEquals(labelMedium, Typography.labelMedium)
    }

    @Test
    @SmallTest
    fun labelSmall() {
        assertEquals(labelSmall, Typography.labelSmall)
    }

    @Test
    @SmallTest
    fun bodyLarge() {
        assertEquals(bodyLarge, Typography.bodyLarge)
    }

    @Test
    @SmallTest
    fun bodyMedium() {
        assertEquals(bodyMedium, Typography.bodyMedium)
    }

    @Test
    @SmallTest
    fun bodySmall() {
        assertEquals(bodySmall, Typography.bodySmall)
    }
}
