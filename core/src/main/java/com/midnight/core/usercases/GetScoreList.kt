package com.midnight.core.usercases

import com.midnight.core.data.UserRepository
import com.midnight.core.domain.QueryUsersScoresCore
import com.midnight.core.helper.DataState
import kotlinx.coroutines.flow.Flow

class GetScoreList constructor(val repository: UserRepository) {
    suspend fun execute(): Flow<DataState<List<QueryUsersScoresCore>?>> {
        return repository.getUsersScores()
    }
}