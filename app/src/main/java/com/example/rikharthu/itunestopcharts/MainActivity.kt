package com.example.rikharthu.itunestopcharts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rikharthu.itunestopcharts.api.ITunesApiService
import com.example.rikharthu.itunestopcharts.api.models.Feed
import dagger.android.AndroidInjection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ITunesApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService.getTopTen().enqueue(object : Callback<Feed> {
            override fun onResponse(call: Call<Feed>?, response: Response<Feed>?) {
                Timber.d(response!!.body().toString())
            }

            override fun onFailure(call: Call<Feed>?, t: Throwable?) {
                Timber.e(t)
            }
        })
    }
}
