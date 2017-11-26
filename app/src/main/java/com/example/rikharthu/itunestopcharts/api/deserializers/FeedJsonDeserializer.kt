package com.example.rikharthu.itunestopcharts.api.deserializers

import com.example.rikharthu.itunestopcharts.api.models.Feed
import com.google.gson.*
import java.lang.reflect.Type

class FeedJsonDeserializer : JsonDeserializer<Feed> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Feed? {
        val feedJsonObj = json?.asJsonObject?.getAsJsonObject("feed")
        if (feedJsonObj != null) {
            val authorJsonObj: JsonObject? = feedJsonObj.getAsJsonObject("author")
            val author = authorJsonObj?.getAsJsonObject("name")?.getLabel()
            val a = 4
        }
//        val feed = Feed()

        return null
    }

    private fun JsonObject.getLabel(): String? {
        return this.get("label")?.extractString()
    }

    private fun JsonElement.extractString(): String? {
        if (this is JsonPrimitive) {
            return asString
        }
        return null
    }
}