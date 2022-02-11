package com.midnight.core.usercases

import com.midnight.core.data.UserScoreRepository
import com.midnight.core.domain.UserScoreModelCore
import com.midnight.core.helper.DataState
import kotlinx.coroutines.flow.Flow

class InsertScore constructor(private val repo:UserScoreRepository) {
    suspend fun execute(data:UserScoreModelCore):Flow<DataState<Long?>>{
        return repo.insertScore(data)
    }
}