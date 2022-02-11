package com.midnight.core.data

import com.midnight.core.domain.UserScoreModelCore

interface UserScoreDataSourceLocal {
    suspend fun insertScore(data:UserScoreModelCore):Long
    suspend fun getUserScore(userName:String):List<UserScoreModelCore>
}