package com.midnight.musictest.framwork.repository.local.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.midnight.musictest.framwork.repository.local.model.UserScoreModelDb

@Dao
interface UserScoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertScore(data:UserScoreModelDb?):Long

}