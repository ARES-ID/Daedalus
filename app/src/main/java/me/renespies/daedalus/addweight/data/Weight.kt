package me.renespies.daedalus.addweight.data

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
    val weight: Int,
    val note: String?,
    val dateTime: ZonedDateTime = ZonedDateTime.now(),
)
