package com.midnight.core.data

import com.midnight.core.domain.LyricModelCore
import com.midnight.core.helper.ApiCallHelper
import com.midnight.core.helper.DataState
import com.midnight.core.helper.NetworkErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class LyricRepository constructor(private val local:LyricDataSourceLocal,private val remote:LyricDataSourceRemote) {
    fun getLyric(isConnected:Boolean,trackId:Long): Flow<DataState<LyricModelCore?>> = flow {
        val localData: LyricModelCore? =local.getLyric(trackId)
        when {
            localData!=null -> {
                emit(DataState.Success(localData))
            }
            isConnected -> {
                ApiCallHelper.safeApiCall { remote.getLyric(trackId) }
                    .catch {
                        emit(DataState.LocalError())
                    }.collect {
                        when (it) {
                            is DataState.Success -> {
                                local.insertLyric(it.value)
                                emit(DataState.Success(it.value))
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
            else -> {
                if (localData==null) {
                    emit(DataState.NetworkError(NetworkErrorType.NetworkConnection()))
                }
            }
        }
    }.flowOn(Dispatchers.IO).catch {
        emit(DataState.LocalError())
    }
}