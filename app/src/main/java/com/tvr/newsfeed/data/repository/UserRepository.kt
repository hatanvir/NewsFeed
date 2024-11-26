package com.tvr.newsfeed.data.repository

import User
import com.tvr.booking.base.ViewState
import com.tvr.newsfeed.data.SharedPref
import com.tvr.newsfeed.data.local.NewsFeedDb
import com.tvr.newsfeed.data.remote.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(
    private var apiInterface: ApiInterface,
    private var newsFeedDb: NewsFeedDb,
    private var sharedPref: SharedPref
) {
    /*
    fetching data from remote
     */
    suspend fun fetchUser(limit: Int): List<User> {
        val userOffset = sharedPref.getValue(SharedPref.PAGE, 0) as Int
        val userResponse = apiInterface.getUsers(limit, userOffset * limit)
        if (userResponse.isSuccessful) {
            val userDtoList = User.toUserDtoList(userResponse.body()?.users);
            newsFeedDb.userDao().insertUsers(userDtoList)
            sharedPref.setValue(SharedPref.PAGE, userOffset+1)
            return userResponse.body()?.users?: emptyList()
        }
        return emptyList()
    }

    suspend fun fetchUserById(id: Long) = flow {
        val user = newsFeedDb.userDao().getUserById(id)
        if (user!=null) {
            emit(ViewState.Success(user))
        } else {
            val userResponse = apiInterface.getUserById(id)
            if (userResponse.isSuccessful) {
                val postDtoList = User.toUserDtoList(listOf(userResponse.body()));
                newsFeedDb.userDao().insertUsers(postDtoList)
                emit(ViewState.Success(userResponse.body()?.let { User.toUserDto(it) }))
            }
        }
    }.flowOn(Dispatchers.IO)
}