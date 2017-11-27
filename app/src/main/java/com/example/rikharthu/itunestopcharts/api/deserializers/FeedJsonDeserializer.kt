package com.example.rikharthu.itunestopcharts.api.deserializers

import com.example.rikharthu.itunestopcharts.api.models.Entry
import com.example.rikharthu.itunestopcharts.api.models.Feed
import com.google.gson.*
import timber.log.Timber
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class FeedJsonDeserializer : JsonDeserializer<Feed> {

    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")

    val gson = Gson()

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Feed? {
        val feedJsonObj = json?.asJsonObject?.getAsJsonObject("feed")
        if (feedJsonObj != null) {
            val authorJsonObj: JsonObject? = feedJsonObj.getAsJsonObject("author")
            val author = authorJsonObj?.getAsJsonObject("name")?.getLabel()
            val updated = feedJsonObj.getAsJsonObject("updated").getLabel()
            val rights = feedJsonObj.getLabel("rights")
            val title = feedJsonObj.getLabel("title")
            val icon = feedJsonObj.getLabel("icon")
            val id = feedJsonObj.getLabel("id")
            val entries = ArrayList<Entry>()
            feedJsonObj.getAsJsonArray("entry")
                    .forEach {
                        val entryJsonObj = it.asJsonObject
                        val name = entryJsonObj.getLabel("im:name")
                        val image = getImage(entryJsonObj.getAsJsonArray("im:image"))
                        val collection = entryJsonObj.getAsJsonObject("im:collection")
                                .getAsJsonObject("im:name").getLabel()
                        val priceJsonObj = entryJsonObj.getAsJsonObject("im:price")
                        val priceLabel = priceJsonObj.getLabel()
                        val priceAmount = priceJsonObj.getAsJsonObject("attributes")
                                .getProperty("amount")
                        val priceCurrency = priceJsonObj.getAsJsonObject("attributes")
                                .getProperty("currency")
                        val rights = entryJsonObj.getLabel("rights")
                        val title = entryJsonObj.getLabel("title")
                        val id = entryJsonObj.getLabel("id")
                        val artist = entryJsonObj.getLabel("im:artist")
                        val artistUrl = entryJsonObj.getAsJsonObject("im:artist")
                                .getAsJsonObject("attributes").getProperty("href")
                        val category = entryJsonObj.getAsJsonObject("category")
                                .getAsJsonObject("attributes")
                                .getLabel()
                        val releaseDate = entryJsonObj.getAsJsonObject("im:releaseDate").getLabel()

                        val entry = Entry(name!!, image, collection!!, priceLabel!!, priceAmount!!.toDouble(),
                                priceCurrency!!, rights!!, title!!, id!!, artist!!, artistUrl!!,
                                category!!, dateFormat.parse(releaseDate)) // TODO parse date
                        entries.add(entry)
                    }
            Timber.d("asd")
        }
//        val feed = Feed()

        return null
    }

    private fun getImage(imagesJsonArray: JsonArray): String {
        val images = ArrayList<Pair<Int, String>>()
        imagesJsonArray.forEach {
            val height = it.asJsonObject.getAsJsonObject("attributes").getProperty("height")!!.toInt()
            val url = it.asJsonObject.getLabel()
            images.add(Pair(height, url!!))
        }
        return images.maxWith(comparator = Comparator<Pair<Int, String>> { left, right ->
            left.first - right.first
        })?.second ?: ""
    }

    private fun JsonObject.getLabel(property: String): String? {
        return this.getAsJsonObject(property)?.getLabel()
    }

    private fun JsonObject.getLabel(): String? {
        return this.getProperty("label")
    }

    private fun JsonObject.getProperty(name: String): String? {
        return this.get(name)?.extractString()
    }

    private fun JsonElement.extractString(): String? {
        if (this is JsonPrimitive) {
            return asString
        }
        return null
    }
}