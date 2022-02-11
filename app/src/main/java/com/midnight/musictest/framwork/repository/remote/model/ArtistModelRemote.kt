package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class ArtistModelRemote(
    @SerializedName("artist_name")
    val artistName:String,
    @SerializedName("artist_id")
    val artistId:Long
)
