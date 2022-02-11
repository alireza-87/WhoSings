package com.midnight.core.data

import com.midnight.core.domain.UserModelCore
import com.midnight.core.domain.UserScoreModelCore
import com.midnight.core.helper.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserScoreRepository constructor(private val local:UserScoreDataSourceLocal) {
    suspend fun insertScore(data:UserScoreModelCore): Flow<DataState<Long?>> = flow {
        try {
            val data=local.insertScore(data)
            emit(DataState.Success(data))
        }catch (ex:Exception){
            emit(DataState.LocalError(ex.toString()))
        }

    }.flowOn(Dispatchers.IO)

}