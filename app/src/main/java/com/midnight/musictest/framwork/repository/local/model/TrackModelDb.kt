package com.midnight.musictest.framwork.repository.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tbl_tracks"
)
data class TrackModelDb(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "track_id")
    val trackId:Long,
    @ColumnInfo(name = "track_name")
    val trackName:String?,
    @ColumnInfo(name = "artist_name")
    val artistName:String?,
    @ColumnInfo(name = "artist_id")
    val artistId:Long

)
