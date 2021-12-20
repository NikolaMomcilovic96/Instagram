package com.raywenderlich.instagramclient.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var username: String,
    var post: Bitmap,
    var description: String,
    var likes: Int
)