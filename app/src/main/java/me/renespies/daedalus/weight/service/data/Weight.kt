package me.renespies.daedalus.weight.service.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import me.renespies.daedalus.database.converters.ZonedDateTimeConverter
import java.time.ZonedDateTime

@Entity
@TypeConverters(ZonedDateTimeConverter::class)
data class Weight(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val weight: Float,
    val note: String?,
    val dateTime: ZonedDateTime = ZonedDateTime.now(),
)
