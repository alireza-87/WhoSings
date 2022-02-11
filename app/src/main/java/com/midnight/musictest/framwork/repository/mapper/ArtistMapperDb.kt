package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.ArtistModelCore
import com.midnight.musictest.framwork.repository.local.model.ArtistModelDb
import javax.inject.Inject

class ArtistMapperDb @Inject constructor() {
    fun toCore(data:List<ArtistModelDb>?):List<ArtistModelCore>?{
        return data?.map {
            ArtistModelCore(
                artistId = it.artistId,
                artistName = it.artistName
            )
        }
    }
}