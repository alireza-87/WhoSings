package com.midnight.musictest.framwork.repository.local.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.midnight.musictest.framwork.repository.local.model.TrackModelDb

@Dao
interface TrackDao {

    @Query("SELECT * FROM tbl_tracks ORDER BY RANDOM() LIMIT 1")
    fun getRandomTrack():TrackModelDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTracks(data:List<TrackModelDb>):List<Long>
}