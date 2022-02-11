package com.midnight.musictest.framwork.repository.remote.interfaces

import com.midnight.musictest.framwork.repository.remote.model.ResponseArtistList
import com.midnight.musictest.framwork.repository.remote.model.ResponseLyricList
import com.midnight.musictest.framwork.repository.remote.model.ResponseTrackList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("chart.tracks.get")
    suspend fun getTopTracks(@Query("chart_name") chartName: String,@Query("page") page:Int,@Query("page_size") pageSize:Int,@Query("country") country:String,@Query("f_has_lyrics") hasLyric:Int) : ResponseTrackList

    @GET("artist.related.get")
    suspend fun getRelatedArtist(@Query("artist_id") artist_id: Long,@Query("page") page:Int,@Query("page_size") pageSize:Int) : ResponseArtistList

    @GET("track.lyrics.get")
    suspend fun getLyric(@Query("track_id") track_id: Long) : ResponseLyricList

}