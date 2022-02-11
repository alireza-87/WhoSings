package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.LyricModelCore
import com.midnight.musictest.framwork.repository.local.model.LyricModelDb
import javax.inject.Inject

class LyricMapperDb @Inject constructor(){
    fun toCore(data:LyricModelDb?):LyricModelCore?{
        data?.let {
            return LyricModelCore(
                trackId = it.trackId,
                lyric = it.lyric
            )
        }
        return null
    }
}