package com.midnight.musictest.framwork.di

import com.midnight.core.data.*
import com.midnight.core.usercases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    @Singleton
    fun provideInsertUser(repository: UserRepository):InsertUser= InsertUser(repository)

    @Provides
    @Singleton
    fun provideGetCurrentUser(repository: UserRepository):GetCurrentUser= GetCurrentUser(repository)

    @Provides
    @Singleton
    fun provideGetOneTrack(repository: TracksRepository):GetOneTrack= GetOneTrack(repository)

    @Provides
    @Singleton
    fun provideGetLyric(repository: LyricRepository):GetLyric= GetLyric(repository)

    @Provides
    @Singleton
    fun provideGetTwoArtist(repository: ArtistRepository):GetTwoArtist= GetTwoArtist(repository)

    @Provides
    @Singleton
    fun provideInsertScore(repository:UserScoreRepository):InsertScore = InsertScore(repository)

    @Provides
    @Singleton
    fun provideGetScoreList(repository: UserRepository) : GetScoreList = GetScoreList(repository)

    @Provides
    @Singleton
    fun provideLogout(repository: UserRepository) : Logout = Logout(repository)

}