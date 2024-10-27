package com.rjspies.daedalus.ui.diagram

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.patrykandpatrick.vico.compose.axis.axisGuidelineComponent
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.component.marker.MarkerComponent
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.marker.Marker
import com.patrykandpatrick.vico.core.scroll.InitialScroll
import com.rjspies.daedalus.R
import com.rjspies.daedalus.data.WeightChartEntry
import com.rjspies.daedalus.ui.common.EmptyScreen
import com.rjspies.daedalus.ui.common.horizontalSpacingM
import com.rjspies.daedalus.ui.common.verticalSpacingM
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeightDiagramScreen(innerPadding: PaddingValues) {
    val viewModel = koinViewModel<WeightDiagramViewModel>()
    val weights by viewModel.weights.collectAsState()
    val entries = rememberSaveable(weights) {
        weights.mapIndexed { index, weight ->
            WeightChartEntry(
                x = index.toFloat(),
                y = weight.value,
                dateTime = weight.dateTime,
            )
        }
    }

    if (entries.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding),
        ) {
            Text(
                text = stringResource(R.string.weight_diagram_title),
                modifier = Modifier.horizontalSpacingM(),
                style = MaterialTheme.typography.headlineMedium,
            )
            Box(Modifier.horizontalSpacingM()) { Chart(entries) }
        }
    } else {
        EmptyScreen(
            painter = painterResource(R.drawable.chart_line_fill),
            contentDescription = stringResource(R.string.weight_diagram_empty_screen_content_description),
            title = stringResource(R.string.weight_diagram_empty_screen_title),
            subtitle = stringResource(R.string.weight_diagram_empty_screen_subtitle),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .verticalSpacingM()
                .horizontalSpacingM(),
        )
    }
}

@Composable
private fun Chart(entries: List<WeightChartEntry>) {
    val lineProducer = remember(entries) { ChartEntryModelProducer(entries) }
    val axisFormatter = remember { WeightDateAxisFormatter }
    val valuesOverrider = remember(entries) { AxisValuesOverrider.fixed(minY = 0f, maxY = entries.maxOf { it.y } * 1.1f) }
    val axisLabel = axisLabelComponent()
    val chartScrollState = rememberChartScrollState()
    val persistentMarker = rememberPersistentMarker(entries.indices)

    LaunchedEffect(entries) {
        chartScrollState.animateScrollBy(chartScrollState.maxValue)
    }

    com.patrykandpatrick.vico.compose.chart.Chart(
        chart = lineChart(
            axisValuesOverrider = valuesOverrider,
            persistentMarkers = persistentMarker,
        ),
        chartModelProducer = lineProducer,
        startAxis = rememberStartAxis(label = axisLabel),
        bottomAxis = rememberBottomAxis(
            valueFormatter = axisFormatter,
            label = axisLabel,
        ),
        marker = rememberMarker(),
        isZoomEnabled = true,
        runInitialAnimation = false,
        chartScrollState = chartScrollState,
        chartScrollSpec = rememberChartScrollSpec(initialScroll = InitialScroll.End),
    )
}

@Composable
private fun rememberPersistentMarker(indices: IntRange): Map<Float, Marker> {
    val label = axisLabelComponent()
    val indicator = shapeComponent(
        shape = Shapes.pillShape,
        color = MaterialTheme.colorScheme.secondary,
    )
    val guideline = axisGuidelineComponent()

    return remember(indices) {
        if (indices.count() == 1) {
            indices.associate {
                it.toFloat() to object : MarkerComponent(
                    label = label,
                    indicator = indicator,
                    guideline = guideline,
                ) {
                    init {
                        indicatorSizeDp = INDICATOR_SIZE_DP
                    }
                }
            }
        } else {
            emptyMap()
        }
    }
}

private const val INDICATOR_SIZE_DP = 6f
