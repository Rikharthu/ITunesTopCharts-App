package com.example.rikharthu.itunestopcharts.api

import android.arch.lifecycle.LiveData
import com.example.rikharthu.itunestopcharts.api.models.ApiResponse
import com.example.rikharthu.itunestopcharts.api.models.FeedResponse
import retrofit2.Call
import retrofit2.http.GET

interface ITunesApiService {

    @GET("/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/json")
    fun getTopTen(): Call<FeedResponse>

    @GET("/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=50/json")
    fun getTop50(): LiveData<ApiResponse<FeedResponse>>

//    @GET("/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit={limit}/json")
//    fun getTopTracks(@Path("limit") limit: Int): LiveData<ApiResponse<FeedResponse>>
}