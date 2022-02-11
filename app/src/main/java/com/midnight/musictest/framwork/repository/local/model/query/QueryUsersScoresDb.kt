package com.midnight.musictest.framwork.repository.local.model.query

import androidx.room.Embedded
import androidx.room.Relation
import com.midnight.musictest.framwork.repository.local.model.UserModelDb
import com.midnight.musictest.framwork.repository.local.model.UserScoreModelDb

class QueryUsersScoresDb {
    @Embedded
    lateinit var user: UserModelDb

    var totalUserScore: Int = 0

    @Transient
    @Relation(
        parentColumn = "user_name",
        entityColumn = "user_name",
        entity = UserScoreModelDb::class
    )
    var userScores: List<UserScoreModelDb>? = null

}