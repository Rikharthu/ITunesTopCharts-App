package com.example.rikharthu.itunestopcharts.api.models

import java.util.*

data class Entry(
        val name: String,
        val image: String,
        val collection: String,
        val priceLabel: String,
        val priceAmount: Double,
        val priceCurrency: String,
        val rights: String,
        val title: String,
        val id: String,
        val artist: String,
        val artistUrl: String,
        val category: String,
        val releaseDate: Date
)