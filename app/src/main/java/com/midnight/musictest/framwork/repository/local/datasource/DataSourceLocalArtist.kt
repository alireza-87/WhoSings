package com.midnight.musictest.framwork.repository.local.datasource

import com.midnight.core.data.ArtistDataSourceLocal
import com.midnight.core.domain.ArtistModelCore
import com.midnight.musictest.framwork.repository.local.interfaces.ArtistDao
import com.midnight.musictest.framwork.repository.mapper.ArtistMapperCore
import com.midnight.musictest.framwork.repository.mapper.ArtistMapperDb
import javax.inject.Inject

class DataSourceLocalArtist @Inject constructor(private val artistDao: ArtistDao,private val mapperDb:ArtistMapperDb,private val mapperCore:ArtistMapperCore):ArtistDataSourceLocal {
    override fun getTwoArtistRandom(): List<ArtistModelCore>? {
        return mapperDb.toCore(artistDao.getTwoArtistRandom())
    }

    override fun insertArtist(data: List<ArtistModelCore>?): List<Long> {
        return artistDao.insertArtists(mapperCore.toDb(data))
    }
}