package com.midnight.musictest.framwork.repository.local.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "tbl_users",
)
data class UserModelDb(
    @PrimaryKey()
    @ColumnInfo(name = "user_name")
    @NonNull val userName:String,

    @ColumnInfo(name = "active")
    @NonNull val active:Boolean,
)
