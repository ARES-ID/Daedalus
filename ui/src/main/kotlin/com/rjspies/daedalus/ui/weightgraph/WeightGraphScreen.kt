package com.rjspies.daedalus.ui.weightgraph

import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AreaChart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.scroll.InitialScroll
import com.rjspies.daedalus.ui.R
import com.rjspies.daedalus.ui.common.horizontalSpacingM
import com.rjspies.daedalus.ui.common.verticalSpacingM
import com.rjspies.daedalus.ui.common.widgets.EmptyScreen
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun WeightGraphScreen() {
    val viewModel: WeightGraphViewModel = koinViewModel()
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            Chart(entries)
        }
    } else {
        EmptyScreen(
            icon = rememberVectorPainter(Icons.Rounded.AreaChart),
            contentDescription = "",
            title = stringResource(R.string.home_empty_screen_title),
            subtitle = stringResource(R.string.home_empty_screen_subtitle),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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

    LaunchedEffect(entries) {
        chartScrollState.animateScrollBy(chartScrollState.maxValue)
    }

    Chart(
        chart = lineChart(axisValuesOverrider = valuesOverrider),
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
