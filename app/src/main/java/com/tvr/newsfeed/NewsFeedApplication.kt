package com.tvr.newsfeed

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created By Tanvir Hasan on 11/25/24 8:18 PM
 * Email: tanvirhasan553@gmail.com
 */
@HiltAndroidApp
class NewsFeedApplication : Application() {
    companion object {
        lateinit var instance: NewsFeedApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}