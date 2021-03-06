package com.example.rikharthu.itunestopcharts.api

import com.example.rikharthu.itunestopcharts.BuildConfig
import com.example.rikharthu.itunestopcharts.api.deserializers.FeedJsonDeserializer
import com.example.rikharthu.itunestopcharts.api.models.FeedResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat

class ITunesAPI {
    private val mITunesApiService: ITunesApiService

    init {
        val client = OkHttpClient().newBuilder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
                })
                .build()
        val gson = GsonBuilder()
                .setLenient()
                .registerTypeAdapter(
                        FeedResponse::class.java, FeedJsonDeserializer()
                )
                .setDateFormat(DateFormat.FULL, DateFormat.FULL)
                .create()
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("http://ax.itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        mITunesApiService = retrofit.create(ITunesApiService::class.java)
    }

    fun getHotTracks(): Call<FeedResponse> {
        return mITunesApiService.getTopTen()
    }
}