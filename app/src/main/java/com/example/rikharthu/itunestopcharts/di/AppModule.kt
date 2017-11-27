package com.example.rikharthu.itunestopcharts.di

import com.example.rikharthu.itunestopcharts.api.ITunesApiService
import com.example.rikharthu.itunestopcharts.api.deserializers.FeedJsonDeserializer
import com.example.rikharthu.itunestopcharts.api.models.Feed
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGithubService(): ITunesApiService {
        val gson = GsonBuilder()
                .setLenient()
                .registerTypeAdapter(
                        Feed::class.java, FeedJsonDeserializer()
                )
                .setDateFormat(DateFormat.FULL, DateFormat.FULL)
                .create()

        return Retrofit.Builder()
                .baseUrl("http://ax.itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create<ITunesApiService>(ITunesApiService::class.java)
    }
}