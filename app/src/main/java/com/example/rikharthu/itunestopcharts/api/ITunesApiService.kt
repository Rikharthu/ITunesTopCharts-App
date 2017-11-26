package com.example.rikharthu.itunestopcharts.api

import com.example.rikharthu.itunestopcharts.api.models.Feed
import retrofit2.Call
import retrofit2.http.GET

interface ITunesApiService {

    @GET("/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=50/json")
    fun getTopTen(): Call<Feed>

//    @GET("/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit={limit}/json")
//    fun getTopTracks(@Path("limit") limit: Int): LiveData<ApiResponse<Feed>>
}