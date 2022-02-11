package com.midnight.core.usercases

import com.midnight.core.data.TracksRepository
import com.midnight.core.domain.TrackModelCore
import com.midnight.core.helper.DataState
import kotlinx.coroutines.flow.Flow

class GetOneTrack constructor(val repo:TracksRepository) {
    suspend fun execute(isConnected:Boolean,page:Int,pageSize:Int,country:String,hasLyric:Int):Flow<DataState<TrackModelCore>>{
        return repo.getOneTrackRandom(isConnected, page, pageSize, country, hasLyric)
    }
}