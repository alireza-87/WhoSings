package com.midnight.core.data

import com.midnight.core.domain.ArtistModelCore

interface ArtistDataSourceRemote {
    suspend fun getRelatedArtist(artistId:Long,page:Int,pageSize:Int):List<ArtistModelCore>?
    suspend fun getTopArtist(page:Int,pageSize:Int,country:String):List<ArtistModelCore>?
}