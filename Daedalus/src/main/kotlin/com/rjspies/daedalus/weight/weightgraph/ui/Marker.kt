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
        lineCount = LABEL_LINE_COUNT,
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
                indicatorSizeDp = INDICATOR_SIZE_DP
            }

            @RequiresApi(Build.VERSION_CODES.Q)
            override fun getInsets(context: MeasureContext, outInsets: Insets, horizontalDimensions: HorizontalDimensions) = with(context) {
                outInsets.top = label.getHeight(context) + LabelBackgroundShape.tickSizeDp.pixels + LABEL_BACKGROUND_SHADOW_RADIUS.pixels * SHADOW_RADIUS_MULTIPLIER - LABEL_BACKGROUND_SHADOW_DY.pixels
            }
        }
    }
}

private const val LABEL_BACKGROUND_SHADOW_RADIUS = 4f
private const val LABEL_BACKGROUND_SHADOW_DY = 2f
private const val LABEL_LINE_COUNT = 1
private const val INDICATOR_SIZE_DP = 12f
private const val GUIDELINE_DASH_LENGTH_DP = 8f
private const val GUIDELINE_GAP_LENGTH_DP = 4f
private const val SHADOW_RADIUS_MULTIPLIER = 1.3f

private val LabelBackgroundShape = MarkerCorneredShape(Corner.FullyRounded)
private val LabelPadding = dimensionsOf(8.dp, 4.dp)
private val GuidelineThickness = 2.dp
private val GuidelineShape = DashedShape(Shapes.pillShape, GUIDELINE_DASH_LENGTH_DP, GUIDELINE_GAP_LENGTH_DP)
