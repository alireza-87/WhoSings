package com.midnight.musictest.framwork.repository.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_artist")
data class ArtistModelDb(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "artist_id")
    val artistId:Long,
    @ColumnInfo(name = "artist_name")
    val artistName:String
)
