package com.tvr.newsfeed.features.user_details

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tvr.newsfeed.R
import com.tvr.newsfeed.data.local.dto.PostDto
import com.tvr.newsfeed.data.local.dto.UserDto
import com.tvr.newsfeed.databinding.ActivityPostDetailsBinding
import com.tvr.newsfeed.databinding.ActivityUserDetailsBinding
import com.tvr.newsfeed.utils.AppKeys

class UserDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(AppKeys.USER, UserDto::class.java)
        } else {
            intent.getParcelableExtra(AppKeys.USER)
        }

        if(user != null){
            binding.user = user
        }
    }
}