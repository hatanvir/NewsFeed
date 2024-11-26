package com.tvr.newsfeed.features.post_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tvr.booking.base.ViewState
import com.tvr.newsfeed.data.local.dto.UserDto
import com.tvr.newsfeed.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailsViewModel @Inject constructor(private var userRepository: UserRepository) :
    ViewModel() {
    var user = MutableSharedFlow<UserDto>()
    lateinit var userDto: UserDto

    fun fetchUser(id: Long) {
        viewModelScope.launch {
            userRepository.fetchUserById(id).collect {
                when (it) {
                    is ViewState.Success -> {
                        it.data?.let {it1->
                            user.emit(it1)
                            userDto = it1
                        }
                    }

                }
            }
        }
    }
}