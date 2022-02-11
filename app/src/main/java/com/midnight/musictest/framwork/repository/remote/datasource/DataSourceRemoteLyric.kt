package com.midnight.musictest.framwork.repository.remote.datasource

import com.midnight.core.data.LyricDataSourceRemote
import com.midnight.core.domain.LyricModelCore
import com.midnight.musictest.framwork.repository.mapper.LyricMapperRemote
import com.midnight.musictest.framwork.repository.remote.interfaces.ApiInterface
import javax.inject.Inject

class DataSourceRemoteLyric @Inject constructor(private val api:ApiInterface,private val mapperRemote:LyricMapperRemote):LyricDataSourceRemote {
    override suspend fun getLyric(trackId: Long): LyricModelCore? {
        return mapperRemote.toCore(api.getLyric(trackId)?.message?.body?.lyrics,trackId)
    }
}