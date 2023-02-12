package me.renespies.daedalus.weight.weightgraph

import com.patrykandpatrick.vico.core.entry.ChartEntry
import java.time.ZonedDateTime

data class WeightChartEntry(
    override val x: Float,
    override val y: Float,
    val dateTime: ZonedDateTime
) : ChartEntry {
    override fun withY(y: Float) = WeightChartEntry(x, y, dateTime)
}
