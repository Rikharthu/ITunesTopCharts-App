package com.example.rikharthu.itunestopcharts.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.rikharthu.itunestopcharts.data.database.ITunesDatabase.Companion.DATABASE_INITIAL_VERSION
import com.example.rikharthu.itunestopcharts.api.models.Entry
import com.example.rikharthu.itunestopcharts.data.models.Track

@Database(entities = arrayOf(Track::class), version = DATABASE_INITIAL_VERSION)
@TypeConverters(Converters::class)
abstract class ITunesDatabase : RoomDatabase() {
    companion object {
        /**
         * Create base tables
         */
        const val DATABASE_INITIAL_VERSION = 1
    }

    abstract fun trackDao(): TrackDao
}