package com.midnight.musictest.framwork.repository.local.datasource

import com.midnight.core.data.UserDataSourceLocal
import com.midnight.core.domain.UserModelCore
import com.midnight.core.domain.QueryUsersScoresCore
import com.midnight.musictest.framwork.repository.local.interfaces.UsersDao
import com.midnight.musictest.framwork.repository.mapper.QueryScoreMapper
import com.midnight.musictest.framwork.repository.mapper.UserMapperCore
import com.midnight.musictest.framwork.repository.mapper.UserMapperDb
import javax.inject.Inject

class DataSourceLocalUser @Inject constructor(private val usersDao: UsersDao,private val mapperDb:UserMapperDb,private val mapperCore:UserMapperCore,private val queryScoreMapper: QueryScoreMapper):UserDataSourceLocal {
    override suspend fun getCurrentUser(): UserModelCore? {
        return mapperDb.toCore(usersDao.getCurrentUser())
    }

    override suspend fun insertUser(data: UserModelCore): Long {
        return usersDao.insertUser(mapperCore.toDb(data)!!)
    }

    override suspend fun getScores(): List<QueryUsersScoresCore>? {
        return queryScoreMapper.toCore(usersDao.getScoreList())
    }

    override suspend fun logout(userName: String): Int {
        return usersDao.logout(userName)
    }
}