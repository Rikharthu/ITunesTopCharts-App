package com.example.rikharthu.itunestopcharts.api.models

import java.util.*

data class Feed(
        val author: String,
        val entry: List<Entry>,
        val updated: Date,
        val rights: String,
        val title: String,
        val icon: String
)