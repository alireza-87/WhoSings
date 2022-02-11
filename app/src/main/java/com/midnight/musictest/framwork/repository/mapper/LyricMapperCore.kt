package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.LyricModelCore
import com.midnight.musictest.framwork.repository.local.model.LyricModelDb
import javax.inject.Inject

class LyricMapperCore @Inject constructor(){
    fun toDb(data:LyricModelCore?):LyricModelDb?{
        data?.let {
            return LyricModelDb(
                trackId = it.trackId,
                lyric = it.lyric
            )
        }
        return null
    }
}