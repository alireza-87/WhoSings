package com.midnight.musictest.framwork.repository.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_lyric")
data class LyricModelDb (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "track_id")
    val trackId:Long,
    @ColumnInfo(name = "lyric")
    val lyric:String)