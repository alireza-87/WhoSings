package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class TrackListModel (
    @SerializedName("track_list")
    val trackList:List<TrackItem>
    )

data class TrackItem(
    @SerializedName("track")
    val track:TrackModelRemote
)