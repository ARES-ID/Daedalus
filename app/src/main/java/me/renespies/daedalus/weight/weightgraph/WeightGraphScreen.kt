package me.renespies.daedalus.weight.weightgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import me.renespies.daedalus.weight.weightgraph.ui.rememberMarker

private const val YAxisValuePadding = 1.01f

@Composable
fun WeightGraphScreen(viewModel: WeightGraphViewModel = viewModel(factory = WeightGraphViewModel.Factory)) {
    val weights by viewModel.weights.collectAsState()
    val entries = remember(weights) {
        weights.mapIndexed { index, weight ->
            WeightChartEntry(
                x = index.toFloat(),
                y = weight.value.toFloat(),
                dateTime = weight.dateTime
            )
        }
    }
    val lineProducer = remember(weights) { ChartEntryModelProducer(entries) }
    val axisFormatter = remember { WeightDateAxisFormatter }
    val valuesOverrider = remember { AxisValuesOverrider.adaptiveYValues(YAxisValuePadding) }

    Chart(
        chart = lineChart(axisValuesOverrider = valuesOverrider),
        chartModelProducer = lineProducer,
        startAxis = startAxis(),
        bottomAxis = bottomAxis(valueFormatter = axisFormatter),
        marker = rememberMarker(),
        isZoomEnabled = false
    )
}
