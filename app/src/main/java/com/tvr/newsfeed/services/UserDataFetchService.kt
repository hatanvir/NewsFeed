package com.tvr.newsfeed.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.tvr.newsfeed.data.repository.UserRepository
import com.tvr.newsfeed.utils.ConstData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

/**
 * Created By Tanvir Hasan on 11/26/24 12:02 AM
 * Email: tanvirhasan553@gmail.com
 */


@AndroidEntryPoint
class UserDataFetchService :
    Service() {
    @Inject
    lateinit var userRepository: UserRepository
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    private val coroutineScope = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler)
    val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("CCCC", "called")
        coroutineScope.launch { userRepository.fetchUser(ConstData.limit) }

        runnable = object : Runnable {
            override fun run() {
                coroutineScope.launch {
                    val lastUserList = userRepository.fetchUser(ConstData.limit)
                    if (lastUserList.isEmpty()) {
                        handler.removeCallbacks(runnable)
                    }
                }
                handler.postDelayed(this, 2 * 60000) //2 mins delay
            }
        }

        handler.post(runnable)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::runnable.isInitialized) {
            handler.removeCallbacks(runnable)
        }
        coroutineScope.cancel()
    }
}