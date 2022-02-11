package com.midnight.musictest.framwork.repository.remote.model

import com.google.gson.annotations.SerializedName

data class ArtistListModel (
    @SerializedName("artist_list")
    val artistList:List<ArtistItem>
    )

data class ArtistItem(
    @SerializedName("artist")
    val artist:ArtistModelRemote
)