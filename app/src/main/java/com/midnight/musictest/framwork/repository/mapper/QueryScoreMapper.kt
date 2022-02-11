package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.QueryUsersScoresCore
import com.midnight.musictest.framwork.repository.local.model.query.QueryUsersScoresDb
import javax.inject.Inject

class QueryScoreMapper @Inject constructor(val userMapperDb: UserMapperDb,val scoreMapperDb: UserScoreMapperDb) {
    fun toCore(data:List<QueryUsersScoresDb>):List<QueryUsersScoresCore>{
        return data?.map {
            QueryUsersScoresCore(
                userModel = userMapperDb.toCore(it.user)!!,
                totalWin = it.totalUserScore,
                scoreList = scoreMapperDb.toCore(it.userScores)
            )
        }
    }
}