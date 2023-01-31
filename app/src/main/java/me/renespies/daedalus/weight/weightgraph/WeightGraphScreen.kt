package me.renespies.daedalus.weight.weightgraph

import android.graphics.Point
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.pow
import kotlin.math.roundToInt

@OptIn(ExperimentalTextApi::class)
@Composable
fun WeightGraphScreen(viewModel: WeightGraphViewModel = viewModel(factory = WeightGraphViewModel.Factory)) {
    val weights by viewModel.weights.collectAsState()
    val data = weights.map { Point(it.dateTime.toEpochSecond().toInt(), it.weight) }
    if (data.size > 1) {
        Graph(coordinates = data.toSet())
    }
}

@ExperimentalTextApi
@Composable
fun Graph(coordinates: Set<Point>) {
    Box(contentAlignment = Alignment.Center) {
        val textMeasurer = rememberTextMeasurer()
        val textStyle = MaterialTheme.typography.overline

        Canvas(
            modifier = Modifier
                .height(800.dp)
                .fillMaxWidth(),
            onDraw = {
                val axisPadding = calculateAxisPadding(coordinates, textMeasurer, textStyle)
                drawYAxis(coordinates, axisPadding, textMeasurer, textStyle)
                drawXAxis(coordinates, axisPadding, textMeasurer, textStyle)
                drawPoints(coordinates, axisPadding, textMeasurer, textStyle)
            }
        )
    }
}

@ExperimentalTextApi
private fun calculateAxisPadding(dataSet: Set<Point>, textMeasurer: TextMeasurer, textStyle: TextStyle): Int {
    val yAxisMaximumWidth = dataSet.maxOf { textMeasurer.width(it, textStyle) }
    val xAxisMaximumHeight = dataSet.maxOf { textMeasurer.height(it, textStyle) }
    return maxOf(yAxisMaximumWidth, xAxisMaximumHeight)
}

@ExperimentalTextApi
private fun DrawScope.drawYAxis(dataSet: Set<Point>, axisPadding: Int, textMeasurer: TextMeasurer, textStyle: TextStyle) {
    val area = calculateYAxisArea(axisPadding)
    val sections = calculateYAxisSections(area, dataSet.map { it.y }.toSet())
    drawLine(
        color = Color.White,
        start = Offset(area.right, 0f),
        end = Offset(area.right, size.height)
    )
    drawRect(
        color = Color.White,
        topLeft = area.topLeft,
        size = area.size,
        style = Stroke()
    )
    sections.forEach { (offset, data) ->
        drawText(
            textMeasurer = textMeasurer,
            text = data.toString(),
            topLeft = Offset(
                offset.x - (textMeasurer.measure(buildAnnotatedString { append(data.toString()) }, textStyle).size.width),
                offset.y - (textMeasurer.measure(buildAnnotatedString { append(data.toString()) }, textStyle).size.height / 2)
            ),
            style = textStyle.copy(color = Color.White)
        )
        drawCircle(
            color = Color.White,
            radius = 5f,
            center = offset
        )
        drawLine(
            color = Color.White,
            start = offset,
            end = Offset(size.width, offset.y)
        )
    }
}

@ExperimentalTextApi
private fun DrawScope.drawXAxis(dataSet: Set<Point>, axisPadding: Int, textMeasurer: TextMeasurer, textStyle: TextStyle) {
    val area = calculateXAxisArea(axisPadding)
    val sections = calculateXAxisSections(area, dataSet.map { it.x }.toSet())
    drawLine(
        color = Color.White,
        start = Offset(0f, area.top),
        end = Offset(size.width, area.top)
    )
    drawRect(
        color = Color.White,
        topLeft = area.topLeft,
        size = area.size,
        style = Stroke()
    )
    sections.forEach { (offset, data) ->
        drawText(
            textMeasurer = textMeasurer,
            text = sections.values.indexOf(data).toString(),
            topLeft = offset,
            style = textStyle.copy(color = Color.White)
        )
        drawCircle(
            color = Color.White,
            radius = 5f,
            center = offset
        )
    }
}

@ExperimentalTextApi
private fun DrawScope.drawPoints(dataSet: Set<Point>, axisPadding: Int, textMeasurer: TextMeasurer, textStyle: TextStyle) {
    val area = calculateDataArea(axisPadding)
    val xAxisStep = area.width / (dataSet.count() - 1)
    val yAxisStep = area.height / (dataSet.count() - 1)
    val offsets = dataSet.sortedBy { it.x }.mapIndexed { index, point ->
        val offset = Offset(xAxisStep * index + area.left, yAxisStep * (dataSet.sortedByDescending { it.y }.indexOf(point)) + area.top)
        drawCircle(
            color = Color.White,
            center = offset,
            radius = 4f
        )
        drawText(
            textMeasurer = textMeasurer,
            text = point.y.toString(),
            topLeft = offset,
            style = textStyle.copy(color = Color.White)
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

private fun calculateYAxisSections(area: Rect, yAxisData: Set<Int>): Map<Offset, Float> {
    val sectionDataStep = (yAxisData.max() - yAxisData.min()) / (yAxisData.count().coerceAtMost(4).toFloat())
    val sectionHeight = area.height / (yAxisData.count().coerceAtMost(4))
    val sectionOffsets = List(yAxisData.count().coerceAtMost(5)) { index ->
        Offset(area.right, area.top + sectionHeight * index)
    }.reversed()
    return sectionOffsets.mapIndexed { index, offset -> offset to (yAxisData.min() + (sectionDataStep * index)).round(1) }.toMap()
}

private fun Float.round(decimals: Int): Float {
    val factor = 10.0.pow(decimals)
    return ((this * factor).roundToInt() / factor).toFloat()
}

private fun calculateXAxisSections(area: Rect, xAxisData: Set<Int>): Map<Offset, Int> {
    val sectionWidth = area.width / (xAxisData.count() - 1)
    val sectionOffsets = List(xAxisData.count()) { index ->
        Offset(area.left + sectionWidth * index, area.top)
    }
    return sectionOffsets.mapIndexed { index, offset -> offset to xAxisData.elementAt(index) }.toMap()
}


private fun DrawScope.calculateDataArea(axisPadding: Int): Rect {
    val yAxisArea = calculateYAxisArea(axisPadding)
    val xAxisArea = calculateXAxisArea(axisPadding)
    return Rect(topLeft = Offset(xAxisArea.left, yAxisArea.top), bottomRight = Offset(xAxisArea.right, yAxisArea.bottom))
}


private fun DrawScope.calculateYAxisArea(padding: Int): Rect {
    val areaHeight = size.height - padding
    return Rect(topLeft = Offset(0f, 30f), bottomRight = Offset(padding.toFloat(), areaHeight - 30f))
}

private fun DrawScope.calculateXAxisArea(padding: Int): Rect {
    return Rect(topLeft = Offset(padding + 30f, size.height - padding), bottomRight = Offset(size.width - 30f, size.height))
}

@ExperimentalTextApi
private fun TextMeasurer.width(point: Point, textStyle: TextStyle): Int {
    return size(point.y * 100, textStyle).width
}

@ExperimentalTextApi
private fun TextMeasurer.height(point: Point, textStyle: TextStyle): Int {
    return size(point.x * 100, textStyle).height
}

@ExperimentalTextApi
private fun TextMeasurer.size(target: Int, textStyle: TextStyle): IntSize {
    val text = buildAnnotatedString { withStyle(textStyle.toSpanStyle()) { append(target.toString()) } }
    return measure(text).size
}
