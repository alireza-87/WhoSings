package com.midnight.core.data

import com.midnight.core.domain.LyricModelCore

interface LyricDataSourceRemote {
    suspend fun getLyric(trackId:Long):LyricModelCore?
}