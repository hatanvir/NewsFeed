package com.tvr.newsfeed.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.tvr.newsfeed.data.local.dto.PostDto
import com.tvr.newsfeed.data.local.dto.UserDto


data class UserWithPosts(
    @Embedded val user: UserDto,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val posts: List<PostDto>
)