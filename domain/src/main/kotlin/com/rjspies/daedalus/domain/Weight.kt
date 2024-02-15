package com.rjspies.daedalus.domain

import java.time.ZonedDateTime
import com.rjspies.daedalus.data.data.Weight as DataWeight

public data class Weight(
    val id: Int = 0,
    val value: Float,
    val note: String? = null,
    val dateTime: ZonedDateTime = ZonedDateTime.now(),
)

internal fun Weight.convert() = DataWeight(
    value = value,
    note = note,
    dateTime = dateTime,
)

internal fun DataWeight.convert() = Weight(
    id = id,
    value = value,
    note = note,
    dateTime = dateTime,
)
