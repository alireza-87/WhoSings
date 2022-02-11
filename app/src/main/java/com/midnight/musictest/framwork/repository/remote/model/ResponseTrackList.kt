package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseTrackList (
    @SerializedName("message")
    val message:MessageTrackList,

)

data class MessageTrackList(
    @SerializedName("header")
    val header:HeaderModel,

    @SerializedName("body")
    val body:TrackListModel,
)