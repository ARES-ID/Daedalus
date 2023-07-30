package com.rjspies.daedalus.weight.weightgraph.ui

import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.core.chart.dimensions.HorizontalDimensions
import com.patrykandpatrick.vico.core.chart.insets.Insets
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.cornered.Corner
import com.patrykandpatrick.vico.core.component.shape.cornered.MarkerCorneredShape
import com.patrykandpatrick.vico.core.context.MeasureContext
import com.patrykandpatrick.vico.core.marker.Marker
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.theme.DaedalusTheme

@Composable
internal fun rememberMarker(): Marker {
    val context = LocalContext.current
    val typeface = remember { ResourcesCompat.getFont(context, R.font.poppins_regular) ?: Typeface.MONOSPACE }
    val indicator = shapeComponent(Shapes.pillShape, DaedalusTheme.colors.primary)

    val labelBackground = shapeComponent(
        shape = MarkerCorneredShape(Corner.FullyRounded),
        color = DaedalusTheme.colors.primary,
    )

    val label = textComponent(
        background = labelBackground,
        lineCount = LabelLineCount,
        padding = LabelPadding,
        typeface = typeface,
        color = DaedalusTheme.colors.onPrimary,
    )

    val guideline = lineComponent(
        DaedalusTheme.colors.primary,
        GuidelineThickness,
        GuidelineShape,
    )

    return remember(label, indicator, guideline) {
        object : MarkerComponent(label, indicator, guideline) {
            init {
                labelFormatter = DaedalusMarkerLabelFormatter
                indicatorSizeDp = IndicatorSizeDp
            }

            @RequiresApi(Build.VERSION_CODES.Q)
            override fun getInsets(context: MeasureContext, outInsets: Insets, horizontalDimensions: HorizontalDimensions) = with(context) {
                outInsets.top = label.getHeight(context) + LabelBackgroundShape.tickSizeDp.pixels + LabelBackgroundShadowRadius.pixels * ShadowRadiusMultiplier - LabelBackgroundShadowDy.pixels
            }
        }
    }
}

private const val LabelBackgroundShadowRadius = 4f
private const val LabelBackgroundShadowDy = 2f
private const val LabelLineCount = 1
private const val IndicatorSizeDp = 12f
private const val GuidelineDashLengthDp = 8f
private const val GuidelineGapLengthDp = 4f
private const val ShadowRadiusMultiplier = 1.3f

private val LabelBackgroundShape = MarkerCorneredShape(Corner.FullyRounded)
private val LabelPadding = dimensionsOf(8.dp, 4.dp)
private val GuidelineThickness = 2.dp
private val GuidelineShape = DashedShape(Shapes.pillShape, GuidelineDashLengthDp, GuidelineGapLengthDp)
