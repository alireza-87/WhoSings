package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.UserScoreModelCore
import com.midnight.musictest.framwork.repository.local.model.UserScoreModelDb
import javax.inject.Inject

class UserScoreMapperDb @Inject constructor() {

    fun toCore(data:UserScoreModelDb?):UserScoreModelCore?{
        data?.let {
            return UserScoreModelCore(
                id = it.id,
                score = it.score,
                isWin = it.isWin,
                userName = it.userName
            )
        }
        return null
    }

    fun toCore(data:List<UserScoreModelDb>?): List<UserScoreModelCore>? {
        return data?.map {
            UserScoreModelCore(
                id = it.id,
                score = it.score,
                isWin = it.isWin,
                userName = it.userName
            )
        }
    }
}