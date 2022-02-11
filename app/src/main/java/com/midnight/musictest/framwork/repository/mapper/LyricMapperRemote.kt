package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.LyricModelCore
import com.midnight.musictest.framwork.repository.local.model.LyricModelDb
import com.midnight.musictest.framwork.repository.remote.model.LyricModelRemote
import javax.inject.Inject

class LyricMapperRemote @Inject constructor(){
    fun toDb(data:LyricModelRemote?,trackId:Long):LyricModelDb?{
        data?.let {
            return LyricModelDb(
                trackId = trackId,
                lyric = it.lyric
            )
        }
        return null
    }

    fun toCore(data:LyricModelRemote?,trackId:Long):LyricModelCore?{
        data?.let {
            return LyricModelCore(
                trackId = trackId,
                lyric = it.lyric
            )
        }
        return null
    }
}