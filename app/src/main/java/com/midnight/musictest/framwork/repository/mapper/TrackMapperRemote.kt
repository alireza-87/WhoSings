package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.TrackModelCore
import com.midnight.musictest.framwork.repository.local.model.TrackModelDb
import com.midnight.musictest.framwork.repository.remote.model.TrackModelRemote
import javax.inject.Inject

class TrackMapperRemote @Inject constructor() {
    fun toCore(data:TrackModelRemote?):TrackModelCore?{
        data?.let {
            return TrackModelCore(
                trackId = it.trackId,
                trackName = it.trackName,
                artistId = it.artistId,
                artistName = it.artistName
            )
        }
        return null
    }

    fun toCore(data:List<TrackModelRemote>?):List<TrackModelCore>?{
        data?.let {
            return data.map {
                TrackModelCore(
                    trackId = it.trackId,
                    trackName = it.trackName,
                    artistId = it.artistId,
                    artistName = it.artistName
                )
            }
        }
        return null
    }
    fun toDb(data:TrackModelRemote?):TrackModelDb?{
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
}