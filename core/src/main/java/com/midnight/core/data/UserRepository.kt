package com.midnight.core.data

import com.midnight.core.domain.QueryUsersScoresCore
import com.midnight.core.domain.UserModelCore
import com.midnight.core.helper.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository constructor(private val dataSourceLocal: UserDataSourceLocal) {
    suspend fun getCurrentUser(): Flow<DataState<UserModelCore?>> = flow {
        try {
            val data=dataSourceLocal.getCurrentUser()
            emit(DataState.Success(data))
        }catch (ex:Exception){
            emit(DataState.LocalError(ex.toString()))
        }

    }.flowOn(Dispatchers.IO)

    suspend fun insertUser(data:UserModelCore):Flow<DataState<Long>> = flow {
        try {
            val data=dataSourceLocal.insertUser(data)
            emit(DataState.Success(data))
        }catch (ex:Exception){
            emit(DataState.LocalError(ex.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getUsersScores(): Flow<DataState<List<QueryUsersScoresCore>?>> = flow {
        try {
            val data=dataSourceLocal.getScores()
            emit(DataState.Success(data))
        }catch (ex:Exception){
            emit(DataState.LocalError(ex.toString()))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun logout(userName:String) : Flow<DataState<Int>> = flow<DataState<Int>> {
        try {
            val result = dataSourceLocal.logout(userName)
            emit(DataState.Success(result))
        }catch (ex:Exception){
            emit(DataState.LocalError(ex.toString()))
        }
    }.flowOn(Dispatchers.IO)

}