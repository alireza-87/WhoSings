package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseArtistList (
    @SerializedName("message")
    val message:MessageArtistList,

)

data class MessageArtistList(
    @SerializedName("header")
    val header:HeaderModel,

    @SerializedName("body")
    val body:ArtistListModel,
)