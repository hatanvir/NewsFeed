package com.tvr.newsfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tvr.newsfeed.data.local.dto.UserDto
import com.tvr.newsfeed.data.local.relations.UserWithPosts

/**
 * Created By Tanvir Hasan on 11/25/24 9:12 PM
 * Email: tanvirhasan553@gmail.com
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id=:id")
    fun getUserById(id: Long): UserDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(posts: List<UserDto>)

    @Transaction
    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserWithPosts(userId: Long): UserWithPosts?
}