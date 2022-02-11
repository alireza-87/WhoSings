package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class ResponseLyricList (
    @SerializedName("message")
    val message:MessageLyricList,

)

data class MessageLyricList(
    @SerializedName("header")
    val header:HeaderModel,

    @SerializedName("body")
    val body:Lyric,
)

data class Lyric(
    @SerializedName("lyrics")
    val lyrics:LyricModelRemote,
)