package com.example.rikharthu.itunestopcharts.api.models

import java.util.*

data class Entry(
        var name: String = "",
        var image: String = "",
        var collection: String = "",
        var priceLabel: String = "",
        var priceAmount: Double = -1.0,
        var priceCurrency: String = "",
        var rights: String = "",
        var title: String = "",
        var id: String,
        var artist: String = "",
        var artistUrl: String = "",
        var category: String = "",
        var releaseDate: Date = Date(0)
)