package me.renespies.daedalus.weight.weightgraph

import android.graphics.Point
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.viewmodel.compose.viewModel
import me.renespies.daedalus.ui.theme.Spacings
import kotlin.math.roundToInt

@OptIn(ExperimentalTextApi::class)
@Composable
fun WeightGraphScreen(
    onBack: () -> Unit,
    viewModel: WeightGraphViewModel = viewModel(factory = WeightGraphViewModel.Factory)
) {
    val weights by viewModel.weights.collectAsState()
    val data = weights.map { Point(it.dateTime.nano, it.weight) }
    if (data.isNotEmpty()) {
        Graph(coordinates = data.toSet())
    }
}

@ExperimentalTextApi
@Composable
fun Graph(coordinates: Set<Point>) {
    Box(
        modifier = Modifier.border(width = Dp.Hairline, color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        val textMeasurer = rememberTextMeasurer()
        val textStyle = MaterialTheme.typography.overline
        val thousand = with(LocalDensity.current) { 1000.toDp() }

        Canvas(
            modifier = Modifier
                .height(thousand)
                .fillMaxWidth()
        ) {
            val axisPadding = calculateAxisPadding(coordinates, textMeasurer, textStyle)
            drawYAxis(coordinates, axisPadding, textMeasurer, textStyle)
            drawXAxis(coordinates, axisPadding)
            drawPoints(coordinates, axisPadding)
        }
    }
}

private fun calculateYAxisSections(area: Rect, yAxisData: Set<Int>): Map<Offset, Int> {
    val sectionDataStep = (yAxisData.max() - yAxisData.min()) / 4f
    val sectionHeight = area.height / 4
    val sectionOffsets = List(5) { index ->
        Offset(area.right, area.top + sectionHeight * index)
    }.reversed()
    return sectionOffsets.mapIndexed { index, offset -> offset to (yAxisData.min() + (sectionDataStep * index)).roundToInt() }.toMap()
}

private fun DrawScope.drawPoints(dataSet: Set<Point>, axisPadding: Int) {
    val area = calculateDataArea(axisPadding)
    val yAxisStep = area.height / (dataSet.count() - 1)
    val xAxisStep = area.width / (dataSet.count() - 1)
    val offsets = dataSet.sortedBy { it.x }.mapIndexed { index, point ->
        val yIndex = dataSet.map { it.y }.indexOf(point.y)
        val offset = Offset((xAxisStep * index) + area.left, area.bottom - (yAxisStep * yIndex))

        drawCircle(
            color = Color.White,
            center = offset,
            radius = 10f
        )
        offset
    }

    offsets.forEachIndexed { index, offset ->
        val futureOffset = offsets.elementAtOrNull(index + 1)
        if (futureOffset != null) {
            drawLine(
                color = Color.White,
                start = offset,
                end = futureOffset
            )
        }
    }
}

private fun DrawScope.calculateDataArea(axisPadding: Int): Rect {
    val yAxisArea = calculateYAxisArea(axisPadding)
    val xAxisArea = calculateXAxisArea(axisPadding)
    return Rect(topLeft = yAxisArea.topRight, bottomRight = xAxisArea.topRight)
}

@ExperimentalTextApi
private fun DrawScope.drawYAxis(dataSet: Set<Point>, axisPadding: Int, textMeasurer: TextMeasurer, textStyle: TextStyle) {
    val area = calculateYAxisArea(axisPadding)
    val sections = calculateYAxisSections(area, dataSet.map { it.y }.toSet())
    drawLine(
        color = Color.White,
        start = Offset(area.right, area.top),
        end = Offset(area.right, area.bottom)
    )
    sections.forEach { (offset, data) ->
        drawText(
            textMeasurer = textMeasurer,
            text = data.toString(),
            topLeft = offset,
            style = textStyle.copy(color = Color.White)
        )
        drawLine(
            color = Color.White,
            start = offset,
            end = Offset(size.width, offset.y)
        )
    }
//    dataSet.forEachIndexed { index, _ ->
//        drawLine(
//            color = Color.White,
//            start = Offset(area.right / 2, (area.height / (dataSet.count() - 1)) * index),
//            end = Offset(area.right, (area.height / (dataSet.count() - 1)) * index)
//        )
//    }
}

private fun DrawScope.drawXAxis(dataSet: Set<Point>, axisPadding: Int) {
    val area = calculateXAxisArea(axisPadding)
    drawLine(
        color = Color.White,
        start = Offset(area.left, area.top),
        end = Offset(area.right, area.top)
    )
    dataSet.forEachIndexed { index, _ ->
        drawLine(
            color = Color.White,
            start = Offset(area.left + ((area.width / (dataSet.count() - 1)) * index), area.top),
            end = Offset(area.left + ((area.width / (dataSet.count() - 1)) * index), area.bottom - area.height / 2)
        )
    }
}

@ExperimentalTextApi
private fun calculateAxisPadding(dataSet: Set<Point>, textMeasurer: TextMeasurer, textStyle: TextStyle): Int {
    val yAxisMaximumWidth = dataSet.maxOf { textMeasurer.width(it, textStyle) }
    val xAxisMaximumHeight = dataSet.maxOf { textMeasurer.height(it, textStyle) }
    return maxOf(yAxisMaximumWidth, xAxisMaximumHeight)
}

private fun DrawScope.calculateYAxisArea(padding: Int): Rect {
    val areaHeight = size.height - padding
    return Rect(topLeft = Offset(0f, 30f), bottomRight = Offset(padding.toFloat(), areaHeight - 30f))
}

private fun DrawScope.calculateXAxisArea(padding: Int): Rect {
    return Rect(topLeft = Offset(padding.toFloat(), size.height - padding), bottomRight = Offset(size.width, size.height))
}

@ExperimentalTextApi
private fun TextMeasurer.width(point: Point, textStyle: TextStyle): Int {
    return size(point.y, textStyle).width
}

@ExperimentalTextApi
private fun TextMeasurer.height(point: Point, textStyle: TextStyle): Int {
    return size(point.x, textStyle).height
}

@ExperimentalTextApi
private fun TextMeasurer.size(target: Int, textStyle: TextStyle): IntSize {
    val text = buildAnnotatedString { withStyle(textStyle.toSpanStyle()) { append(target.toString()) } }
    return measure(text).size
}
