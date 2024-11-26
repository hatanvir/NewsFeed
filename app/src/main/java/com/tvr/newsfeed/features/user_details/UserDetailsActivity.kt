package com.tvr.newsfeed.features.user_details

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.tvr.newsfeed.R
import com.tvr.newsfeed.data.local.dto.PostDto
import com.tvr.newsfeed.data.local.dto.UserDto
import com.tvr.newsfeed.databinding.ActivityPostDetailsBinding
import com.tvr.newsfeed.databinding.ActivityUserDetailsBinding
import com.tvr.newsfeed.features.post.PostViewModel
import com.tvr.newsfeed.utils.AppKeys
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding
    private val viewmodel: UserDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = viewmodel
        setContentView(binding.root)

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(AppKeys.USER, UserDto::class.java)
        } else {
            intent.getParcelableExtra(AppKeys.USER)
        }

        if (user != null) {
            viewmodel.fetchUserWithPost(user.id)
        }

        handleUser()
    }

    private fun handleUser() {
       lifecycleScope.launch {
           viewmodel.user.collectLatest {
               binding.user = it
           }
       }
    }
}