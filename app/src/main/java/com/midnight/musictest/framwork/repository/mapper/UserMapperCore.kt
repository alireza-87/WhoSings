package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.UserModelCore
import com.midnight.musictest.framwork.repository.local.model.UserModelDb
import javax.inject.Inject

class UserMapperCore @Inject constructor() {
    fun toDb(data:UserModelCore?):UserModelDb?{
        data?.let {
            return UserModelDb(
                userName = it.userName,
                active = it.active
            )
        }
        return null
    }
}