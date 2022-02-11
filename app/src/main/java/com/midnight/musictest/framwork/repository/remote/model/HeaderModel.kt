package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class HeaderModel (
    @SerializedName("status_code")
    val statusCode:String
        )