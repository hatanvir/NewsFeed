package com.tvr.newsfeed.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tvr.newsfeed.data.local.dto.PostDto

/**
 * Created By Tanvir Hasan on 11/25/24 9:12 PM
 * Email: tanvirhasan553@gmail.com
 */
@Dao
interface PostDao {
    @Query("SELECT * FROM posts ORDER BY id ASC LIMIT :limit OFFSET :offset")
    fun getPosts(limit: Int, offset: Int): List<PostDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<PostDto>)
}