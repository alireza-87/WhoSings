package com.midnight.musictest.framwork.di

import android.content.Context
import androidx.room.Room
import com.midnight.musictest.framwork.repository.local.DataBase
import com.midnight.musictest.framwork.repository.local.interfaces.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    private val DB_NAME = "music.db"

    // ************** PROVIDE DATABASE **************

    @Provides
    fun provideUserScoreDao(dataBase: DataBase): UserScoreDao {
        return dataBase.userScoreDao()
    }

    @Provides
    fun provideUserDao(dataBase: DataBase):UsersDao{
        return dataBase.userDao()
    }

    @Provides
    fun provideArtistDao(dataBase: DataBase):ArtistDao{
        return dataBase.artistDao()
    }

    @Provides
    fun provideLyricDao(dataBase: DataBase):LyricDao{
        return dataBase.lyricDao()
    }

    @Provides
    fun provideTrackDao(dataBase: DataBase):TrackDao{
        return dataBase.trackDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): DataBase {
        return Room.databaseBuilder(
            appContext,
            DataBase::class.java,
            DB_NAME
        ).build()
    }

}