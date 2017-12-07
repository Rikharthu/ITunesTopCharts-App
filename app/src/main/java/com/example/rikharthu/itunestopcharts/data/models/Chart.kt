package com.example.rikharthu.itunestopcharts.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Chart(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val author: String = "",
        val updated: Date = Date(0),
        val rights: String = "",
        val title: String = "",
        @Ignore
        val tracks: List<Track>? = ArrayList()
)