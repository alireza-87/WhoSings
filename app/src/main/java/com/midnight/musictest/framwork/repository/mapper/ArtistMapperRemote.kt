package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.ArtistModelCore
import com.midnight.musictest.framwork.repository.local.model.ArtistModelDb
import com.midnight.musictest.framwork.repository.remote.model.ArtistModelRemote
import javax.inject.Inject

class ArtistMapperRemote @Inject constructor() {
    fun toCore(data:List<ArtistModelRemote>?):List<ArtistModelCore>?{
        return data?.map {
            ArtistModelCore(
                artistId = it.artistId,
                artistName = it.artistName
            )
        }
    }

    fun toDb(data:List<ArtistModelRemote>?):List<ArtistModelDb>?{
        return data?.map {
            ArtistModelDb(
                artistId = it.artistId,
                artistName = it.artistName
            )
        }
    }
}