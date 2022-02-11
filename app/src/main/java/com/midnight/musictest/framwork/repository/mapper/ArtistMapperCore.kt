package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.ArtistModelCore
import com.midnight.musictest.framwork.repository.local.model.ArtistModelDb
import javax.inject.Inject

class ArtistMapperCore @Inject constructor() {
    fun toDb(data:List<ArtistModelCore>?):List<ArtistModelDb>?{
        return data?.map {
            ArtistModelDb(
                artistId = it.artistId,
                artistName = it.artistName
            )
        }
    }
}