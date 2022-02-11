package com.midnight.core.data

import com.midnight.core.domain.TrackModelCore

interface TracksDataSourceRemote {
    suspend fun getTopTracks(chartName: String,page:Int,pageSize:Int,country:String,fHasLyrics:Int):List<TrackModelCore>?
}