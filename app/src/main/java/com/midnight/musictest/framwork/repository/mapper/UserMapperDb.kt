package com.midnight.musictest.framwork.repository.mapper

import com.midnight.core.domain.UserModelCore
import com.midnight.musictest.framwork.repository.local.model.UserModelDb
import javax.inject.Inject

class UserMapperDb @Inject constructor() {
    fun toCore(data:UserModelDb?):UserModelCore?{
        data?.let {
            return UserModelCore(
                userName = it.userName,
                active = it.active
            )
        }
        return null
    }
}