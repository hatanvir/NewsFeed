package com.tvr.newsfeed.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tvr.newsfeed.data.models.Reactions
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Entity("posts")
@Parcelize
data class PostDto (
    @PrimaryKey
    val id: Long,
    val title: String,
    val body: String,
    val tags: String,
    val views: Long,
    val userId: Long,
    val likes: Long,
    val dislikes: Long
):Parcelable