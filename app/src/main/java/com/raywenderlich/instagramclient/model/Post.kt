package com.raywenderlich.instagramclient.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var username: String,
    var post: Int,
    var description: String = "",
    var likes: Int = 0
) {
    fun likePost() {
        
    }
}