package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.TrackModelCore
import com.midnight.musictest.framwork.repository.local.model.TrackModelDb
import javax.inject.Inject

class TrackMapperCore @Inject constructor() {
    fun toDb(data:TrackModelCore?):TrackModelDb?{
        data?.let {
            return TrackModelDb(
                trackId = it.trackId,
                trackName = it.trackName,
                artistId = it.artistId,
                artistName = it.artistName
            )
        }
        return null
    }

    fun toDb(data:List<TrackModelCore>?):List<TrackModelDb>{
        data?.let {
            return data.map {
                TrackModelDb(
                    trackId = it.trackId,
                    trackName = it.trackName,
                    artistId = it.artistId,
                    artistName = it.artistName
                )
            }
        }
        return emptyList()
    }
}