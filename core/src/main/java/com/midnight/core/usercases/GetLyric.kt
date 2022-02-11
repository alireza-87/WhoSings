package com.midnight.core.usercases

import com.midnight.core.data.LyricRepository
import com.midnight.core.domain.LyricModelCore
import com.midnight.core.helper.DataState
import kotlinx.coroutines.flow.Flow

class GetLyric constructor(val repository: LyricRepository) {
    fun execute(isConnected:Boolean,trackId:Long): Flow<DataState<LyricModelCore?>> {
        return repository.getLyric(isConnected, trackId)
    }
}