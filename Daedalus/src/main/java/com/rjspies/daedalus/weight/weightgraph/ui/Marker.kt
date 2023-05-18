package com.rjspies.daedalus.weight.weightgraph.ui

import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.overlayingComponent
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.core.chart.insets.Insets
import com.patrykandpatrick.vico.core.chart.segment.SegmentProperties
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.DashedShape
import com.patrykandpatrick.vico.core.component.shape.ShapeComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.component.shape.cornered.Corner
import com.patrykandpatrick.vico.core.component.shape.cornered.MarkerCorneredShape
import com.patrykandpatrick.vico.core.context.MeasureContext
import com.patrykandpatrick.vico.core.extension.copyColor
import com.patrykandpatrick.vico.core.marker.Marker
import com.rjspies.daedalus.R

@Composable
internal fun rememberMarker(): Marker {
    val labelBackgroundColor = MaterialTheme.colorScheme.surface
    val context = LocalContext.current
    val typeface = remember { ResourcesCompat.getFont(context, R.font.poppins_regular) ?: Typeface.MONOSPACE }
    val labelBackground = remember(labelBackgroundColor) {
        ShapeComponent(labelBackgroundShape, labelBackgroundColor.toArgb())
    }
    val label = textComponent(
        background = labelBackground,
        lineCount = LabelLineCount,
        padding = labelPadding,
        typeface = typeface
    )
    val indicatorInnerComponent = shapeComponent(Shapes.pillShape, MaterialTheme.colorScheme.surface)
    val indicatorCenterComponent = shapeComponent(Shapes.pillShape, Color.White)
    val indicatorOuterComponent = shapeComponent(Shapes.pillShape, Color.White)
    val indicator = overlayingComponent(
        outer = indicatorOuterComponent,
        inner = overlayingComponent(
            outer = indicatorCenterComponent,
            inner = indicatorInnerComponent,
            innerPaddingAll = indicatorInnerAndCenterComponentPaddingValue
        ),
        innerPaddingAll = indicatorCenterAndOuterComponentPaddingValue
    )
    val guideline = lineComponent(
        MaterialTheme.colorScheme.onSurface.copy(GuidelineAlpha),
        guidelineThickness,
        guidelineShape
    )
    return remember(label, indicator, guideline) {
        object : MarkerComponent(label, indicator, guideline) {
            init {
                indicatorSizeDp = IndicatorSizeDp
                onApplyEntryColor = { entryColor ->
                    indicatorOuterComponent.color = entryColor.copyColor(IndicatorOuterComponentAlpha)
                    with(indicatorCenterComponent) {
                        color = entryColor
                        setShadow(radius = IndicatorCenterComponentShadowRadius, color = entryColor)
                    }
                }
            }

            @RequiresApi(Build.VERSION_CODES.Q)
            override fun getInsets(context: MeasureContext, outInsets: Insets, segmentProperties: SegmentProperties) = with(context) {
                outInsets.top = label.getHeight(context) + labelBackgroundShape.tickSizeDp.pixels + LabelBackgroundShadowRadius.pixels * ShadowRadiusMultiplier - LabelBackgroundShadowDy.pixels
            }
        }
    }
}

private const val LabelBackgroundShadowRadius = 4f
private const val LabelBackgroundShadowDy = 2f
private const val LabelLineCount = 1
private const val GuidelineAlpha = .2f
private const val IndicatorSizeDp = 36f
private const val IndicatorOuterComponentAlpha = 32
private const val IndicatorCenterComponentShadowRadius = 12f
private const val GuidelineDashLengthDp = 8f
private const val GuidelineGapLengthDp = 4f
private const val ShadowRadiusMultiplier = 1.3f

private val labelBackgroundShape = MarkerCorneredShape(Corner.FullyRounded)
private val labelHorizontalPaddingValue = 8.dp
private val labelVerticalPaddingValue = 4.dp
private val labelPadding = dimensionsOf(labelHorizontalPaddingValue, labelVerticalPaddingValue)
private val indicatorInnerAndCenterComponentPaddingValue = 5.dp
private val indicatorCenterAndOuterComponentPaddingValue = 10.dp
private val guidelineThickness = 2.dp
private val guidelineShape = DashedShape(Shapes.pillShape, GuidelineDashLengthDp, GuidelineGapLengthDp)
