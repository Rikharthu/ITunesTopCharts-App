package com.example.rikharthu.itunestopcharts

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.rikharthu.itunestopcharts.api.ITunesApiService
import com.example.rikharthu.itunestopcharts.data.DataManager
import com.example.rikharthu.itunestopcharts.data.models.Track
import com.example.rikharthu.itunestopcharts.repository.TrackRepository
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Observer<List<Track>> {

    @Inject
    lateinit var apiService: ITunesApiService
    @Inject
    lateinit var dataManager: DataManager
    @Inject
    lateinit var repo: TrackRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        apiService.getTopTen().enqueue(object : Callback<FeedResponse> {
//            override fun onResponse(call: Call<FeedResponse>?, response: Response<FeedResponse>?) {
//                Timber.d(response!!.body().toString())
//            }
//
//            override fun onFailure(call: Call<FeedResponse>?, t: Throwable?) {
//                Timber.e(t)
//            }
//        })

//        apiService.getTop50().observe(this, this)

        repo.loadTracks().observe(this, this)
    }

    override fun onChanged(t: List<Track>?) {
        Timber.d("List of tracks updated:")
        t?.forEach {
            Timber.d(it.toString())
        }
    }
}
