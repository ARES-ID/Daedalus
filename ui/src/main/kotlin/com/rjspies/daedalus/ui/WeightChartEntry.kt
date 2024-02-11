package com.rjspies.daedalus.ui

import com.patrykandpatrick.vico.core.entry.ChartEntry
import java.time.ZonedDateTime

internal data class WeightChartEntry(
    override val x: Float,
    override val y: Float,
    val dateTime: ZonedDateTime,
) : ChartEntry {
    override fun withY(y: Float) = WeightChartEntry(x, y, dateTime)
}
