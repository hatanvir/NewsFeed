package com.tvr.newsfeed.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    internal fun retrofit(
        @ApplicationContext context: Context,
        gsonConverterFactory: GsonConverterFactory
    ) = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    internal fun gsonConvertFactory() = GsonConverterFactory.create()

}