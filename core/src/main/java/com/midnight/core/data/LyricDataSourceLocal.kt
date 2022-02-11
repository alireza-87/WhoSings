package com.midnight.core.data

import com.midnight.core.domain.LyricModelCore

interface LyricDataSourceLocal {
    fun getLyric(trackId:Long):LyricModelCore?
    fun insertLyric(data:LyricModelCore?):Long
}