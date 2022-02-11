package com.midnight.core.usercases

import com.midnight.core.data.UserRepository
import com.midnight.core.domain.UserModelCore
import com.midnight.core.helper.DataState
import kotlinx.coroutines.flow.Flow

class InsertUser constructor(private val repo:UserRepository) {
    suspend fun execute(data:UserModelCore): Flow<DataState<Long>> {
        return repo.insertUser(data)
    }
}