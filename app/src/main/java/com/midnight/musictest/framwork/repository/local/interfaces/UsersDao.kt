package com.midnight.musictest.framwork.repository.local.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.midnight.musictest.framwork.repository.local.model.UserModelDb
import com.midnight.musictest.framwork.repository.local.model.query.QueryUsersScoresDb

@Dao
interface UsersDao {

    @Query("SELECT * FROM tbl_users WHERE active = 1")
    fun getCurrentUser():UserModelDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(data:UserModelDb):Long

    @Query("SELECT tbl_users.* , (SELECT SUM(score) from tbl_user_score where tbl_users.user_name=tbl_user_score.user_name) as totalUserScore  FROM tbl_users ORDER BY totalUserScore desc")
    fun getScoreList():List<QueryUsersScoresDb>

    @Query("UPDATE tbl_users SET active = 0 WHERE user_name = :userName")
    fun logout(userName:String):Int
}