package com.midnight.musictest.framwork.repository.remote.datasource

import com.midnight.core.data.TracksDataSourceRemote
import com.midnight.core.domain.TrackModelCore
import com.midnight.musictest.framwork.repository.mapper.TrackMapperRemote
import com.midnight.musictest.framwork.repository.remote.interfaces.ApiInterface
import com.midnight.musictest.framwork.repository.remote.model.TrackItem
import javax.inject.Inject

class DataSourceRemoteTrack @Inject constructor(private val apiInterface: ApiInterface,private val mapperRemote: TrackMapperRemote):TracksDataSourceRemote {
    override suspend fun getTopTracks(
        chartName: String,
        page: Int,
        pageSize: Int,
        country: String,
        fHasLyrics: Int
    ): List<TrackModelCore>? {
        return mapperRemote.toCore(apiInterface.getTopTracks(chartName,page,pageSize,country,fHasLyrics)?.message?.body?.trackList)
    }

}

private fun TrackMapperRemote.toCore(trackList: List<TrackItem>): List<TrackModelCore>? {
    var resultList:ArrayList<TrackModelCore> = ArrayList()
    trackList.forEach {
        resultList.add(TrackModelCore(it.track.trackId,it.track.trackName,it.track.artistName,it.track.artistId))
    }
    return resultList
}
