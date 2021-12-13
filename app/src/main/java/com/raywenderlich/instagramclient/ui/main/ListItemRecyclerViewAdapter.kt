package com.raywenderlich.instagramclient.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.instagramclient.R
import com.raywenderlich.instagramclient.databinding.ListItemViewBinding
import com.raywenderlich.instagramclient.db.UserDao
import com.raywenderlich.instagramclient.model.Post
import com.raywenderlich.instagramclient.viewmodel.UserViewModel

class ListItemRecyclerViewAdapter :
    RecyclerView.Adapter<ListItemViewHolder>() {

    private var postList = emptyList<Post>()
    private lateinit var userDao: UserDao
    private lateinit var userViewModel: UserViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding =
            ListItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {

        val currentItem = postList[position]
        var likesCount = currentItem.likes
        var liked = false
        val likesText = "$likesCount likes"

        fun checkDesc(): Boolean {
            return holder.binding.descriptionTextView.text == ""
        }

        holder.binding.profileImageView.setImageResource(R.drawable.doncic)
        holder.binding.usernameTextView.text = currentItem.username
        holder.binding.postImageView.setImageResource(currentItem.post)
        holder.binding.likesTextView.text = likesText
        if (checkDesc()) {
            holder.binding.bottomUsernameTextView.text = currentItem.username
            holder.binding.descriptionTextView.text = currentItem.description
        } else {
            holder.binding.bottomUsernameTextView.isVisible = false
            holder.binding.descriptionTextView.isVisible = false
        }
        holder.binding.likeButton.setOnClickListener {
            if (!liked) {
                likesCount += 1
                holder.binding.likesTextView.text = "$likesCount likes"
                liked = true
            } else {
                likesCount -= 1
                liked = false
                holder.binding.likesTextView.text = "$likesCount likes"
            }
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun setData(post: List<Post>) {
        this.postList = post
        notifyDataSetChanged()
    }
}