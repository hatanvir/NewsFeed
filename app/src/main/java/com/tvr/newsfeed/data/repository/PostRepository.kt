package com.tvr.newsfeed.data.repository

import com.tvr.booking.base.ViewState
import com.tvr.newsfeed.data.local.NewsFeedDb
import com.tvr.newsfeed.data.local.dto.PostDto
import com.tvr.newsfeed.data.local.dto.UserDto
import com.tvr.newsfeed.data.models.Post
import com.tvr.newsfeed.data.remote.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created By Tanvir Hasan on 11/25/24 9:03 PM
 * Email: tanvirhasan553@gmail.com
 */
class PostRepository @Inject constructor(
    private var apiInterface: ApiInterface,
    private var newsFeedDb: NewsFeedDb
) {
    /*
    fetching data from remote
     */
    suspend fun fetchPost(limit: Int, page: Int) = flow {
        if (page > 0) {
            emit(ViewState.PaginationLoading)
        } else {
            emit(ViewState.Loading)
        }

        val postFromLocal = newsFeedDb.postDao().getPosts(limit, page * limit)
        if (postFromLocal.isNotEmpty()) {
            emit(ViewState.Success(postFromLocal))
        } else {
            val postResponse = apiInterface.getPosts(limit, page * limit)
            if (postResponse.isSuccessful) {
                val postDtoList = Post.toPostDtoList(postResponse.body()?.posts);
                newsFeedDb.postDao().insertPosts(postDtoList)
                emit(ViewState.Success(postDtoList))
            } else {
                emit(ViewState.Error(postResponse.message()))
            }
        }
    }.catch {
        emit(ViewState.Error(it.message))
    }.flowOn(Dispatchers.IO)
}