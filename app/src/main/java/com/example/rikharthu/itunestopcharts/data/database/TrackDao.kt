package com.example.rikharthu.itunestopcharts.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.rikharthu.itunestopcharts.data.models.Track

@Dao
interface TrackDao {
    companion object {
        const val TABLE_NAME = "tracks"
    }

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<Track>

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllV2(): LiveData<List<Track>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(track: Track): Long

    @Query("SELECT * FROM $TABLE_NAME WHERE id = :id")
    fun findById(id: Long): Track

    @Query("SELECT * FROM $TABLE_NAME WHERE title = :title LIMIT 1")
    fun findByTitle(title: String): Track

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg tracks: Track): List<Long>
}