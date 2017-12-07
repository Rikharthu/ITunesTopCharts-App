package com.example.rikharthu.itunestopcharts.data

import android.arch.persistence.room.Room
import android.content.Context
import com.example.rikharthu.itunestopcharts.data.database.ITunesDatabase

class DataManager(val database:ITunesDatabase) {

//    val database: ITunesDatabase

    companion object {
        const val DATABASE_NAME = "contoso-database.sqlite"
    }

}