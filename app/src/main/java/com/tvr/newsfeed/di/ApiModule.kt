package com.tvr.newsfeed.di

import com.tvr.newsfeed.data.remote.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    internal fun apiService(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)
}