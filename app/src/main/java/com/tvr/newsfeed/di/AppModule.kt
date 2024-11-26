package com.tvr.newsfeed.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.tvr.newsfeed.R
import com.tvr.newsfeed.data.SharedPref
import com.tvr.newsfeed.data.local.NewsFeedDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    internal fun getRoomDb(@ApplicationContext context: Context): NewsFeedDb {
        return Room.databaseBuilder(
            context, NewsFeedDb::class.java,
            "newsfeed_db"
        ).build();
    }

    /**
     * providing shared SharedPreferences instance here
     */
    @Provides
    @Singleton
    internal fun getSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    }

    /**
     * getting shared pref instance here
     */
    @Provides
    @Singleton
    internal fun getSharedPref(sharedPreferences: SharedPreferences): SharedPref{
        return SharedPref(sharedPreferences)
    }
}