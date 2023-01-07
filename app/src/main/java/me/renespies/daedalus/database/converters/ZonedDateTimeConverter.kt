package me.renespies.daedalus.database.converters

import androidx.room.TypeConverter
import java.time.ZonedDateTime

object ZonedDateTimeConverter {
    @TypeConverter
    fun stringToZonedDateTime(dateTime: String): ZonedDateTime = ZonedDateTime.parse(dateTime)

    @TypeConverter
    fun zonedDateTimeToString(zonedDateTime: ZonedDateTime): String = zonedDateTime.toString()
}
