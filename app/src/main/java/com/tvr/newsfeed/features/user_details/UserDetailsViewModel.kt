package com.tvr.newsfeed.features.user_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tvr.booking.base.ViewState
import com.tvr.booking.listeners.ItemClickListener
import com.tvr.newsfeed.data.local.dto.PostDto
import com.tvr.newsfeed.data.local.dto.UserDto
import com.tvr.newsfeed.data.repository.UserRepository
import com.tvr.newsfeed.features.post.adapters.PostRecyclerViewAdapter
import com.tvr.newsfeed.features.post.adapters.UserPostRecyclerViewAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(private var userRepository: UserRepository) : ViewModel(),ItemClickListener {
    var postRecyclerViewAdapter = UserPostRecyclerViewAdapter()
    var user = MutableSharedFlow<UserDto>()

    fun fetchUserWithPost(id: Long) {
        viewModelScope.launch {
            userRepository.fetchUserWithPost(id).collect {
                when (it) {
                    is ViewState.Success -> {
                        it.data?.let { it1 ->
                            user.emit(it1.user)
                            postRecyclerViewAdapter.setData(it1.posts,this@UserDetailsViewModel)
                            postRecyclerViewAdapter.notifyDataSetChanged()
                        }
                    }

                    is ViewState.Error -> TODO()
                    ViewState.Loading -> TODO()
                    ViewState.PaginationLoading -> TODO()
                }
            }
        }
    }

    override fun onClick(data: Any) {
        TODO("Not yet implemented")
    }
}