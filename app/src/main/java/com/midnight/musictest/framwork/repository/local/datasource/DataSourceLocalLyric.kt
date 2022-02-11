package com.midnight.musictest.framwork.repository.local.datasource

import com.midnight.core.data.LyricDataSourceLocal
import com.midnight.core.domain.LyricModelCore
import com.midnight.musictest.framwork.repository.local.interfaces.LyricDao
import com.midnight.musictest.framwork.repository.mapper.LyricMapperCore
import com.midnight.musictest.framwork.repository.mapper.LyricMapperDb
import javax.inject.Inject

class DataSourceLocalLyric @Inject constructor(private val lyricDao: LyricDao,private val mapperDb:LyricMapperDb,private val mapperCore:LyricMapperCore):LyricDataSourceLocal {
    override fun getLyric(trackId: Long): LyricModelCore? {
        return mapperDb.toCore(lyricDao.selectLyric(trackId))
    }

    override fun insertLyric(data: LyricModelCore?): Long {
        return lyricDao.insertLyric(mapperCore.toDb(data))
    }
}