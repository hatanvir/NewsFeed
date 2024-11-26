package com.tvr.newsfeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tvr.newsfeed.data.local.dao.PostDao
import com.tvr.newsfeed.data.local.dao.UserDao
import com.tvr.newsfeed.data.local.dto.PostDto
import com.tvr.newsfeed.data.local.dto.UserDto


@Database(
    entities = [
        PostDto::class,
        UserDto::class
    ],
    version = 1
)
abstract class NewsFeedDb : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
}