package com.rjspies.daedalus.ui.diagram

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
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

@Composable
internal fun rememberMarker(): Marker {
    val indicator = shapeComponent(
        shape = Shapes.pillShape,
        color = MaterialTheme.colorScheme.primary,
    )

    val labelBackground = shapeComponent(
        shape = MarkerCorneredShape(Corner.FullyRounded),
        color = MaterialTheme.colorScheme.primary,
    )

    val label = textComponent(
        background = labelBackground,
        lineCount = LABEL_LINE_COUNT,
        padding = LabelPadding,
        color = MaterialTheme.colorScheme.onPrimary,
    )

    val guideline = lineComponent(
        thickness = GuidelineThickness,
        shape = GuidelineShape,
        color = MaterialTheme.colorScheme.primary,
    )

    return remember(label, indicator, guideline) {
        object : MarkerComponent(label, indicator, guideline) {
            init {
                labelFormatter = DaedalusMarkerLabelFormatter
                indicatorSizeDp = INDICATOR_SIZE_DP
            }

            override fun getInsets(
                context: MeasureContext,
                outInsets: Insets,
                horizontalDimensions: HorizontalDimensions,
            ) = with(context) {
                outInsets.top = label.getHeight(context) +
                    LabelBackgroundShape.tickSizeDp.pixels +
                    (LABEL_BACKGROUND_SHADOW_RADIUS.pixels * SHADOW_RADIUS_MULTIPLIER) -
                    LABEL_BACKGROUND_SHADOW_DY.pixels
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
