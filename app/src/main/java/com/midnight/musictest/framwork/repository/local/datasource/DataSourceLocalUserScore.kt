package com.midnight.musictest.framwork.repository.local.datasource

import com.midnight.core.data.UserScoreDataSourceLocal
import com.midnight.core.domain.UserScoreModelCore
import com.midnight.musictest.framwork.repository.local.interfaces.UserScoreDao
import com.midnight.musictest.framwork.repository.mapper.UserScoreMapperCore
import com.midnight.musictest.framwork.repository.mapper.UserScoreMapperDb
import javax.inject.Inject

class DataSourceLocalUserScore @Inject constructor(private val userScoreDao: UserScoreDao,private val mapperCore:UserScoreMapperCore,private val mapperDb: UserScoreMapperDb):UserScoreDataSourceLocal {
    override suspend fun insertScore(data: UserScoreModelCore): Long {
        return userScoreDao.insertScore(mapperCore.toDb(data))
    }

    override suspend fun getUserScore(userName: String): List<UserScoreModelCore> {
        TODO("Not yet implemented")
    }
}