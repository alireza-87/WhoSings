package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.TrackModelCore
import com.midnight.musictest.framwork.repository.local.model.TrackModelDb
import javax.inject.Inject

class TrackMapperDb @Inject constructor() {
    fun toCore(data:TrackModelDb?):TrackModelCore?{
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
}