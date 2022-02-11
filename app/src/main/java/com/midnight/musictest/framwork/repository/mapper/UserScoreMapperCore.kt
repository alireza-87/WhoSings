package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.UserScoreModelCore
import com.midnight.musictest.framwork.repository.local.model.UserScoreModelDb
import javax.inject.Inject

class UserScoreMapperCore @Inject constructor() {
    fun toDb(data:UserScoreModelCore):UserScoreModelDb?{
        data?.let {
            return UserScoreModelDb(
                userName = it.userName,
                isWin = it.isWin,
                score = it.score,
                id = it.id
            )
        }
        return null
    }
}