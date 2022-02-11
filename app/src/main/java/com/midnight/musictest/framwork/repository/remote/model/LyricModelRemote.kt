package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class LyricModelRemote (
    @SerializedName("lyrics_id")
    val lyricId:Long,
    @SerializedName("lyrics_body")
    val lyric:String)