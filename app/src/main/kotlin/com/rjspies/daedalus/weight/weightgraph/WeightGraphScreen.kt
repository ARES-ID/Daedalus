package com.rjspies.daedalus.weight.weightgraph

import android.graphics.Typeface
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DataThresholding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.res.ResourcesCompat
import com.patrykandpatrick.vico.compose.axis.axisLabelComponent
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.rjspies.daedalus.R
import com.rjspies.daedalus.ui.horizontalSpacingM
import com.rjspies.daedalus.ui.verticalSpacingM
import com.rjspies.daedalus.ui.widgets.EmptyScreen
import com.rjspies.daedalus.weight.weightgraph.ui.rememberMarker
import org.koin.androidx.compose.koinViewModel

private const val Y_AXIS_VALUE_PADDING = 1.01f

@Composable
fun WeightGraphScreen() {
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
        Chart(entries)
    } else {
        EmptyScreen(
            icon = Icons.Outlined.DataThresholding,
            contentDescription = stringResource(R.string.extensions_content_description_chart),
            title = stringResource(R.string.home_empty_screen_title),
            subtitle = stringResource(R.string.home_empty_screen_subtitle),
            modifier = Modifier
                .fillMaxWidth()
                .verticalSpacingM()
                .horizontalSpacingM(),
        )
    }
}

@Composable
private fun Chart(entries: List<WeightChartEntry>) {
    val context = LocalContext.current
    val lineProducer = remember(entries) { ChartEntryModelProducer(entries) }
    val axisFormatter = remember { WeightDateAxisFormatter }
    val valuesOverrider = remember { AxisValuesOverrider.adaptiveYValues(Y_AXIS_VALUE_PADDING) }
    val typeface = remember { ResourcesCompat.getFont(context, R.font.poppins_regular) ?: Typeface.MONOSPACE }
    val axisLabel = axisLabelComponent(typeface = typeface)

    Chart(
        chart = lineChart(axisValuesOverrider = valuesOverrider),
        chartModelProducer = lineProducer,
        startAxis = rememberStartAxis(label = axisLabel),
        bottomAxis = rememberBottomAxis(
            valueFormatter = axisFormatter,
            label = axisLabel,
        ),
        marker = rememberMarker(),
        isZoomEnabled = false,
    )
}
