package com.rjspies.daedalus.ui.weightgraph

import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.values.ChartValues
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

internal object WeightDateAxisFormatter : AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
    override fun formatValue(
        value: Float,
        chartValues: ChartValues,
    ): CharSequence {
        val weightEntries = chartValues.chartEntryModel.entries.filterIsInstance<List<WeightChartEntry>>().flatten()
        return if (weightEntries.isNotEmpty()) {
            val weight = weightEntries.elementAtOrNull(value.toInt())
            weight?.dateTime?.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)).orEmpty()
        } else {
            ""
        }
    }
}
