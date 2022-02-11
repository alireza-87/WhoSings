package com.midnight.musictest.framwork.di

import com.midnight.core.data.UserDataSourceLocal
import com.midnight.musictest.framwork.repository.local.datasource.*
import com.midnight.musictest.framwork.repository.local.interfaces.*
import com.midnight.musictest.framwork.repository.mapper.*
import com.midnight.musictest.framwork.repository.remote.datasource.DataSourceRemoteArtist
import com.midnight.musictest.framwork.repository.remote.datasource.DataSourceRemoteLyric
import com.midnight.musictest.framwork.repository.remote.datasource.DataSourceRemoteTrack
import com.midnight.musictest.framwork.repository.remote.interfaces.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    @Singleton
    fun provideUserDataSourceLocal(usersDao: UsersDao,mapperCore: UserMapperCore,mapperDb: UserMapperDb,queryScoreMapper: QueryScoreMapper):DataSourceLocalUser = DataSourceLocalUser(usersDao,mapperDb, mapperCore,queryScoreMapper)

    @Provides
    @Singleton
    fun provideTrackDataSourceLocal(trackDao: TrackDao,mapperCore: TrackMapperCore,mapperDb: TrackMapperDb):DataSourceLocalTrack = DataSourceLocalTrack(trackDao, mapperDb, mapperCore)

    @Provides
    @Singleton
    fun provideTrackDataSourceRemote(api: ApiInterface,mapperRemote: TrackMapperRemote):DataSourceRemoteTrack = DataSourceRemoteTrack(api, mapperRemote)

    @Provides
    @Singleton
    fun provideArtistDataSourceLocal(artistDao: ArtistDao,mapperCore: ArtistMapperCore,mapperDb: ArtistMapperDb):DataSourceLocalArtist = DataSourceLocalArtist(artistDao, mapperDb, mapperCore)

    @Provides
    @Singleton
    fun provideArtistDataSourceRemote(api: ApiInterface,mapperRemote: ArtistMapperRemote):DataSourceRemoteArtist = DataSourceRemoteArtist(api, mapperRemote)

    @Provides
    @Singleton
    fun provideLyricDataSourceLocal(lyricDao: LyricDao,mapperCore: LyricMapperCore,mapperDb: LyricMapperDb):DataSourceLocalLyric = DataSourceLocalLyric(lyricDao, mapperDb, mapperCore)

    @Provides
    @Singleton
    fun provideLyricDataSourceRemote(api: ApiInterface,mapperRemote: LyricMapperRemote):DataSourceRemoteLyric = DataSourceRemoteLyric(api, mapperRemote)

    @Provides
    @Singleton
    fun provideUserScoreDataSourceLocal(userScoreDao: UserScoreDao,mapperCore:UserScoreMapperCore,mapperDb:UserScoreMapperDb):DataSourceLocalUserScore=
        DataSourceLocalUserScore(userScoreDao, mapperCore, mapperDb)

}