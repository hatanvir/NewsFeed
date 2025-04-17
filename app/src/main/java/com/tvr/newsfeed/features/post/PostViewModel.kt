package com.tvr.newsfeed.features.post

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tvr.booking.base.ViewState
import com.tvr.booking.listeners.ItemClickListener
import com.tvr.newsfeed.data.local.dto.PostDto
import com.tvr.newsfeed.data.repository.PostRepository
import com.tvr.newsfeed.features.post.adapters.PostRecyclerViewAdapter
import com.tvr.newsfeed.features.post.adapters.UserPostRecyclerViewAdapter
import com.tvr.newsfeed.utils.ConstData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created By Tanvir Hasan on 11/25/24 8:53 PM
 * Email: tanvirhasan553@gmail.com
 */
@HiltViewModel
class PostViewModel @Inject constructor(private var postRepository: PostRepository) : ViewModel(),ItemClickListener {
    var postRecyclerViewAdapter = PostRecyclerViewAdapter()
    var progressBarVisibility = MutableStateFlow(false)
    var paginateProgressBarVisibility = MutableStateFlow(false)
    var isLoading = MutableStateFlow(false)
    var selectedPost = MutableSharedFlow<PostDto>()


    init {
        fetchPosts(ConstData.limit, 0)
    }
    fun fetchPosts(limit: Int, page: Int) {
        viewModelScope.launch {
            try {
                postRepository.fetchPost(limit, page).collect {
                    when (it) {
                        is ViewState.Loading -> {
                            progressBarVisibility.emit(true)
                            paginateProgressBarVisibility.emit(false)
                            isLoading.emit(true)
                            Log.d("LLL","LOading")
                        }

                        is ViewState.PaginationLoading -> {
                            progressBarVisibility.emit(false)
                            paginateProgressBarVisibility.emit(true)
                            isLoading.emit(true)

                            Log.d("LLL","LOadingPage")
                        }

                        is ViewState.Success -> {

                            it.data?.let { it1 ->
                                progressBarVisibility.emit(false)
                                paginateProgressBarVisibility.emit(false)
                                isLoading.emit(false)

                                postRecyclerViewAdapter.setData(it1,this@PostViewModel)
                                postRecyclerViewAdapter.notifyDataSetChanged()
                            }
                        }

                        is ViewState.Error -> {
                            progressBarVisibility.value = false
                            paginateProgressBarVisibility.value = false


                            Log.d("ERRR",""+it)
                        }
                    }
                }
            }catch (e:Exception){

            }
        }
    }

    override fun onClick(data: Any) {
        viewModelScope.launch {
            selectedPost.emit(data as PostDto)
        }
    }
}