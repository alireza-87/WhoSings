package com.midnight.musictest.framwork.repository.local.interfaces

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.midnight.musictest.framwork.repository.local.model.ArtistModelDb

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtists(data:List<ArtistModelDb>?):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(data:ArtistModelDb?):Long

    @Query("SELECT * FROM tbl_artist ORDER BY RANDOM() LIMIT 2")
    fun getTwoArtistRandom():List<ArtistModelDb>
}