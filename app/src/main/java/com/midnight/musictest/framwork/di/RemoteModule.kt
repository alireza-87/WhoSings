package com.midnight.musictest.framwork.di

import com.midnight.musictest.BuildConfig
import com.midnight.musictest.framwork.repository.remote.interfaces.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    // ************** PROVIDE REMOTE **************

    @Provides
    fun provideBaseUrl() = "https://api.musixmatch.com/ws/1.1/"

    @Singleton
    @Provides
    fun provideOkHttpClient() : OkHttpClient =  if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor {
                val original: Request = it.request()
                val originalHttpUrl: HttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", BuildConfig.API_KEY)
                    .build()
                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()
                it.proceed(request)
            }
            .addInterceptor(loggingInterceptor)

            .build()
    }else{
        OkHttpClient
            .Builder()
            .addInterceptor {
                val original: Request = it.request()
                val originalHttpUrl: HttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", BuildConfig.API_KEY)
                    .build()
                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()
                it.proceed(request)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

}