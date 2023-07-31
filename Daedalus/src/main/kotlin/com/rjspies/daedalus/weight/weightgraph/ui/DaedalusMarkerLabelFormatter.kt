package com.rjspies.daedalus.weight.weightgraph.ui

import android.text.Spannable
import com.patrykandpatrick.vico.core.chart.values.ChartValues
import com.patrykandpatrick.vico.core.extension.appendCompat
import com.patrykandpatrick.vico.core.extension.sumOf
import com.patrykandpatrick.vico.core.extension.transformToSpannable
import com.patrykandpatrick.vico.core.marker.Marker
import com.patrykandpatrick.vico.core.marker.MarkerLabelFormatter

object DaedalusMarkerLabelFormatter : MarkerLabelFormatter {
    private const val PATTERN = "%.02f"

    override fun getLabel(
        markedEntries: List<Marker.EntryModel>,
        chartValues: ChartValues,
    ): CharSequence = markedEntries.transformToSpannable(
        prefix = if (markedEntries.size > 1) PATTERN.format(markedEntries.sumOf { it.entry.y }) + " (" else "",
        postfix = if (markedEntries.size > 1) ")" else "",
        separator = "; ",
    ) { model ->
        appendCompat(
            text = PATTERN.format(model.entry.y),
            what = Any(),
            flags = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE,
        )
    }
}
