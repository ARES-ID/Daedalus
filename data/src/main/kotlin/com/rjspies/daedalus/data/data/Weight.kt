package com.rjspies.daedalus.data.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rjspies.daedalus.data.converters.ZonedDateTimeConverter
import java.time.ZonedDateTime

@Entity
@TypeConverters(ZonedDateTimeConverter::class)
public data class Weight(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: Float,
    val note: String?,
    val dateTime: ZonedDateTime = ZonedDateTime.now(),
)
