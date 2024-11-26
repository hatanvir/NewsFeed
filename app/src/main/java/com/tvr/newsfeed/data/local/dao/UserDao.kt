package com.tvr.newsfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tvr.newsfeed.data.local.dto.UserDto

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
}