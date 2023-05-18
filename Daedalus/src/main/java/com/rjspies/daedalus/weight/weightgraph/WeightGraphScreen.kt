package com.rjspies.daedalus.weight.weightgraph

import android.graphics.Typeface
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.rjspies.daedalus.R
import com.rjspies.daedalus.compose.VerticalSpacerM
import com.rjspies.daedalus.weight.weightgraph.ui.rememberMarker

private const val YAxisValuePadding = 1.01f

@Composable
fun WeightGraphScreen(viewModel: WeightGraphViewModel = viewModel(factory = WeightGraphViewModel.Factory)) {
    val weights by viewModel.weights.collectAsState()
    val entries = rememberSaveable(weights) {
        weights.mapIndexed { index, weight ->
            WeightChartEntry(
                x = index.toFloat(),
                y = weight.value,
                dateTime = weight.dateTime
            )
        }
    }
    val context = LocalContext.current
    val lineProducer = remember(weights) { ChartEntryModelProducer(entries) }
    val axisFormatter = remember { WeightDateAxisFormatter }
    val valuesOverrider = remember { AxisValuesOverrider.adaptiveYValues(YAxisValuePadding) }
    val typeface = remember { ResourcesCompat.getFont(context, R.font.poppins_regular) ?: Typeface.MONOSPACE }
    val axisLabel = axisLabelComponent(typeface = typeface)

    Column {
        weights.forEach {
            Text(
                text = it.value.toString(),
                style = MaterialTheme.typography.bodySmall
            )
            VerticalSpacerM()
        }
        Chart(
            chart = lineChart(axisValuesOverrider = valuesOverrider),
            chartModelProducer = lineProducer,
            startAxis = startAxis(label = axisLabel),
            bottomAxis = bottomAxis(
                valueFormatter = axisFormatter,
                label = axisLabel
            ),
            marker = rememberMarker(),
            isZoomEnabled = false
        )
    }
}
