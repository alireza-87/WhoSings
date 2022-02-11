package com.midnight.musictest.framwork.di

import com.midnight.core.data.*
import com.midnight.musictest.framwork.repository.local.datasource.*
import com.midnight.musictest.framwork.repository.remote.datasource.DataSourceRemoteArtist
import com.midnight.musictest.framwork.repository.remote.datasource.DataSourceRemoteLyric
import com.midnight.musictest.framwork.repository.remote.datasource.DataSourceRemoteTrack
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(dataSourceLocalUser: DataSourceLocalUser): UserRepository = UserRepository(dataSourceLocalUser)

    @Provides
    @Singleton
    fun provideUserScoreRepository(dataSourceLocalUserScore: DataSourceLocalUserScore): UserScoreRepository = UserScoreRepository(dataSourceLocalUserScore)

    @Provides
    @Singleton
    fun provideTrackRepository(dataSourceLocalTrack: DataSourceLocalTrack,dataSourceRemoteTrack: DataSourceRemoteTrack): TracksRepository = TracksRepository(dataSourceLocalTrack,dataSourceRemoteTrack)

    @Provides
    @Singleton
    fun provideArtistRepository(dataSourceLocalArtist: DataSourceLocalArtist,dataSourceRemoteArtist: DataSourceRemoteArtist) : ArtistRepository = ArtistRepository(dataSourceLocalArtist,dataSourceRemoteArtist)

    @Provides
    @Singleton
    fun provideLyricRepository(dataSourceLocalLyric: DataSourceLocalLyric,dataSourceRemoteLyric: DataSourceRemoteLyric):LyricRepository = LyricRepository(dataSourceLocalLyric,dataSourceRemoteLyric)
}