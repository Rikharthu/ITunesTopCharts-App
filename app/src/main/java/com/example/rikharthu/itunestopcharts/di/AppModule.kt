package com.example.rikharthu.itunestopcharts.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.rikharthu.itunestopcharts.api.ITunesApiService
import com.example.rikharthu.itunestopcharts.api.deserializers.FeedJsonDeserializer
import com.example.rikharthu.itunestopcharts.api.models.ApiResponse
import com.example.rikharthu.itunestopcharts.api.models.FeedResponse
import com.example.rikharthu.itunestopcharts.data.DataManager
import com.example.rikharthu.itunestopcharts.data.database.ITunesDatabase
import com.example.rikharthu.itunestopcharts.repository.TrackRepository
import com.example.rikharthu.itunestopcharts.util.LiveDataCallAdapterFactory
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
                        FeedResponse::class.java, FeedJsonDeserializer()
                )
                .setDateFormat(DateFormat.FULL, DateFormat.FULL)
                .create()

        return Retrofit.Builder()
                .baseUrl("http://ax.itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(LiveDataCallAdapterFactory<ApiResponse<*>>())
                .build()
                .create<ITunesApiService>(ITunesApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppContext(app: Application) = app.applicationContext

    @Singleton
    @Provides
    fun provideITunesDatabase(context: Context): ITunesDatabase =
            Room.databaseBuilder(context.applicationContext, ITunesDatabase::class.java, DataManager.DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()

    @Singleton
    @Provides
    fun provideDataManager(database: ITunesDatabase) = DataManager(database)

    @Singleton
    @Provides
    fun provideTrackRepository(database: ITunesDatabase, apiService: ITunesApiService) = TrackRepository(database, apiService)
}