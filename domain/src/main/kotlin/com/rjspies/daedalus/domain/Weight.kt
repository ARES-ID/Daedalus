package com.rjspies.daedalus.domain

import java.time.ZonedDateTime
import com.rjspies.daedalus.data.data.Weight as DataWeight

/**
 * An intermediate object used to abstract [DataWeight] in order to not publicize it beyond the domain module.
 */
public data class Weight(
    val id: Int = 0,
    val value: Float,
    val note: String? = null,
    val dateTime: ZonedDateTime = ZonedDateTime.now(),
)

internal fun Weight.toDataWeight() = DataWeight(
    id = id,
    value = value,
    note = note,
    dateTime = dateTime,
)

internal fun DataWeight.toWeight() = Weight(
    id = id,
    value = value,
    note = note,
    dateTime = dateTime,
)
