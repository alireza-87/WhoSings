package com.midnight.musictest.framwork.repository.remote.datasource

import com.midnight.core.data.ArtistDataSourceRemote
import com.midnight.core.domain.ArtistModelCore
import com.midnight.core.domain.TrackModelCore
import com.midnight.musictest.framwork.repository.mapper.ArtistMapperRemote
import com.midnight.musictest.framwork.repository.mapper.TrackMapperRemote
import com.midnight.musictest.framwork.repository.remote.interfaces.ApiInterface
import com.midnight.musictest.framwork.repository.remote.model.ArtistItem
import com.midnight.musictest.framwork.repository.remote.model.TrackItem
import javax.inject.Inject

class DataSourceRemoteArtist @Inject constructor(private val api:ApiInterface,private val mapperRemote:ArtistMapperRemote):ArtistDataSourceRemote {
    override suspend fun getRelatedArtist(
        artistId: Long,
        page: Int,
        pageSize: Int
    ): List<ArtistModelCore>? {
        return mapperRemote.toCore(api.getRelatedArtist(artistId,page,pageSize)?.message?.body?.artistList)
    }
}

private fun ArtistMapperRemote.toCore(trackList: List<ArtistItem>?): List<ArtistModelCore>? {
    var resultList:ArrayList<ArtistModelCore> = ArrayList()
    trackList?.forEach {
        resultList.add(ArtistModelCore(it.artist.artistId,it.artist.artistName))
    }
    return resultList
}
