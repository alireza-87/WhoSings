package com.midnight.musictest.framwork.repository.local.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.midnight.musictest.framwork.repository.local.model.LyricModelDb

@Dao
interface LyricDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLyric(data:LyricModelDb?):Long

    @Query("SELECT * FROM tbl_lyric WHERE track_id = :trackId")
    fun selectLyric(trackId:Long):LyricModelDb
}