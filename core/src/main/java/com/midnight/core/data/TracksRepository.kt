package com.midnight.core.data

import com.midnight.core.domain.TrackModelCore
import com.midnight.core.helper.ApiCallHelper
import com.midnight.core.helper.DataState
import com.midnight.core.helper.NetworkErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class TracksRepository constructor(private val local:TracksDataSourceLocal,private val  remote:TracksDataSourceRemote) {
    suspend fun getOneTrackRandom(isConnected:Boolean,page:Int,pageSize:Int,country:String,hasLyric:Int):Flow<DataState<TrackModelCore>> = flow {
        val localData : TrackModelCore? = local.getRandomTrack()
        localData?.let {
            emit(DataState.Success(localData))
        }
        if (!isConnected && localData==null) {
            emit(DataState.NetworkError(NetworkErrorType.NetworkConnection()))
        } else {
            ApiCallHelper.safeApiCall { remote.getTopTracks("top",page,pageSize,country,hasLyric) }
                .catch {
                    emit(DataState.LocalError())
                }.collect {
                    when (it) {
                        is DataState.Success -> {
                            local.insertTracks(it.value)
                            if (localData==null)
                                local.getRandomTrack()?.let { it2->
                                    emit(DataState.Success(it2))
                                }
                        }
                        is DataState.LocalError -> {
                            if (localData==null) {
                                emit(DataState.LocalError())
                            }

                        }
                        is DataState.NetworkError -> {
                            if (localData==null) {
                                emit(DataState.NetworkError(it.networkError))
                            }
                        }
                        else -> emit(DataState.LocalError())
                    }
                }
        }
    }.flowOn(Dispatchers.IO).catch {
        emit(DataState.LocalError())
    }
}