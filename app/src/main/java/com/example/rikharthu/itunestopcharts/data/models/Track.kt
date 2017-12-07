package com.example.rikharthu.itunestopcharts.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tracks")
data class Track(
        @PrimaryKey(autoGenerate = true)
        var id: Long,
        val title: String = "",
        val artist: String = "",
        val price: String = "",
        val album: String = "",
        @ColumnInfo(name = "playback_url")
        val playbackUrl: String = "",
        val category: String = "",
        val rights: String = "",
        @ColumnInfo(name = "is_favorite")
        var isFavorite: Boolean = false
){

}