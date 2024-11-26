package com.tvr.newsfeed.features.post.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tvr.booking.listeners.ItemClickListener
import com.tvr.newsfeed.data.local.dto.PostDto
import com.tvr.newsfeed.databinding.PostRvItemBinding

/**
 * Created By Tanvir Hasan on 11/25/24 8:59 PM
 * Email: tanvirhasan553@gmail.com
 */
class UserPostRecyclerViewAdapter : RecyclerView.Adapter<UserPostRecyclerViewAdapter.PostViewHolder>() {
    private val postList = mutableListOf<PostDto>()
    private lateinit var itemClickListener: ItemClickListener
    fun setData(postList: List<PostDto>,itemClickListener: ItemClickListener ) {
        this.postList.addAll(postList)
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostRvItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            ),
            itemClickListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class PostViewHolder(
        private var postRvItemBinding: PostRvItemBinding,
        private var itemClickListener: ItemClickListener
    ) :
        ViewHolder(postRvItemBinding.root) {
        fun bind(postDto: PostDto) {
            postRvItemBinding.post = postDto
            postRvItemBinding.root.setOnClickListener {
                itemClickListener.onClick(postDto)
            }
        }
    }
}