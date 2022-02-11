package com.midnight.musictest.framwork.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.midnight.musictest.framwork.repository.local.interfaces.*
import com.midnight.musictest.framwork.repository.local.model.*

@Database(
    entities = [UserScoreModelDb::class,UserModelDb::class,ArtistModelDb::class,LyricModelDb::class,TrackModelDb::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase:RoomDatabase() {
    abstract fun userScoreDao():UserScoreDao
    abstract fun userDao():UsersDao
    abstract fun artistDao():ArtistDao
    abstract fun lyricDao():LyricDao
    abstract fun trackDao():TrackDao

}