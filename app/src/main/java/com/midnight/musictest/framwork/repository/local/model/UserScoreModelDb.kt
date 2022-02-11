package com.midnight.musictest.framwork.repository.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tbl_user_score"
)
data class UserScoreModelDb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "user_name")
    @NonNull val userName: String,

    @ColumnInfo(name = "is_win")
    val isWin:Boolean,

    @ColumnInfo(name = "score")
    val score:Int)