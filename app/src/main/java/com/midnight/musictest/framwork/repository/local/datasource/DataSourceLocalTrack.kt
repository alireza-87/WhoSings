package com.midnight.musictest.framwork.repository.local.datasource

import com.midnight.core.data.TracksDataSourceLocal
import com.midnight.core.domain.TrackModelCore
import com.midnight.musictest.framwork.repository.local.interfaces.TrackDao
import com.midnight.musictest.framwork.repository.mapper.TrackMapperCore
import com.midnight.musictest.framwork.repository.mapper.TrackMapperDb
import javax.inject.Inject

class DataSourceLocalTrack @Inject constructor(private val trackDao: TrackDao, private val mapperDb: TrackMapperDb,private val mapperCore:TrackMapperCore):TracksDataSourceLocal {
    override fun getRandomTrack(): TrackModelCore? {
        return mapperDb.toCore(trackDao.getRandomTrack())
    }

    override fun insertTracks(data: List<TrackModelCore>?): List<Long> {
        return trackDao.insertTracks(data = mapperCore.toDb(data))
    }
}