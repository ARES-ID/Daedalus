package com.rjspies.daedalus.data

import androidx.room.TypeConverter
import java.time.ZonedDateTime

public object ZonedDateTimeConverter {
    @TypeConverter
    public fun stringToZonedDateTime(dateTime: String): ZonedDateTime = ZonedDateTime.parse(dateTime)

    @TypeConverter
    public fun zonedDateTimeToString(zonedDateTime: ZonedDateTime): String = zonedDateTime.toString()
}
