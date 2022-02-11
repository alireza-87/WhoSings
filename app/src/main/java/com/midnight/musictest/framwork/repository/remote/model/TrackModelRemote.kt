package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class TrackModelRemote (
    @SerializedName("track_id")
    val trackId:Long,
    @SerializedName("track_name")
    val trackName:String?,
    @SerializedName("artist_name")
    val artistName:String?,
    @SerializedName("artist_id")
    val artistId:Long

)