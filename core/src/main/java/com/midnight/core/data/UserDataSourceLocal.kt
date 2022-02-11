package com.midnight.core.data

import com.midnight.core.domain.UserModelCore
import com.midnight.core.domain.QueryUsersScoresCore

interface UserDataSourceLocal {
    suspend fun getCurrentUser():UserModelCore?
    suspend fun insertUser(data:UserModelCore):Long
    suspend fun getScores():List<QueryUsersScoresCore>?
    suspend fun logout(userName:String):Int
}