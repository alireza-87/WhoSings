package com.midnight.core.data

import com.midnight.core.domain.TrackModelCore

interface TracksDataSourceLocal {
    fun getRandomTrack():TrackModelCore?
    fun insertTracks(data:List<TrackModelCore>?):List<Long>
}