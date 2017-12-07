package com.example.rikharthu.itunestopcharts.repository

import android.arch.lifecycle.LiveData
import com.example.rikharthu.itunestopcharts.api.ITunesApiService
import com.example.rikharthu.itunestopcharts.api.models.Entry
import com.example.rikharthu.itunestopcharts.api.models.FeedResponse
import com.example.rikharthu.itunestopcharts.data.database.ITunesDatabase
import com.example.rikharthu.itunestopcharts.data.models.Track
import com.example.rikharthu.itunestopcharts.util.RateLimiter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.concurrent.TimeUnit


class TrackRepository(private val db: ITunesDatabase,
                      private val apiService: ITunesApiService
) {

    private val repoListRateLimit = RateLimiter<Int>(10, TimeUnit.MINUTES)

    fun loadTracks(): LiveData<List<Track>> {
        apiService.getTopTen().enqueue(object : Callback<FeedResponse> {
            override fun onResponse(call: Call<FeedResponse>?, response: Response<FeedResponse>?) {
                //TODO parse and save
//                val tracks = response.body().entry
                val tracks = mutableListOf<Track>()
                for (entry in response!!.body()!!.entry) {
                    tracks.add(entry.asTrack())
//                    db.trackDao().insert(entry.asTrack())
                }
                db.trackDao().insertAll(*tracks.toTypedArray())
            }

            override fun onFailure(call: Call<FeedResponse>?, t: Throwable?) {
                Timber.e(t)
            }
        })
        return db.trackDao().getAllV2()
    }
}

private fun Entry.asTrack(): Track {
    return Track(0, name, artist, priceLabel, "TODO Album", "TODO Play url",
            category, rights, false)
}
