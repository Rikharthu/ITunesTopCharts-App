package com.example.rikharthu.itunestopcharts.data.database

import android.arch.persistence.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun timestampToDate(value: Long?): Date? {
        return if (value == null)
            null
        else
            Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}