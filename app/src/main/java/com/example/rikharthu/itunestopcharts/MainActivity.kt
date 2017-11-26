package com.example.rikharthu.itunestopcharts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rikharthu.itunestopcharts.api.ITunesAPI
import com.example.rikharthu.itunestopcharts.api.models.Feed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = ITunesAPI()
        api.getHotTracks().enqueue(object : Callback<Feed> {
            override fun onResponse(call: Call<Feed>?, response: Response<Feed>?) {
                Timber.d(response!!.body().toString())
            }

            override fun onFailure(call: Call<Feed>?, t: Throwable?) {
                Timber.e(t)
            }
        })
    }
}
