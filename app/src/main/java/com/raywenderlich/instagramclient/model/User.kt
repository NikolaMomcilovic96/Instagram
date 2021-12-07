package com.raywenderlich.instagramclient.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var username: String,
    var password: String,
    var profilePicture: Int = 0,
    var posts: String = "0 Posts",
    var followers: String = "0 Followers",
    var following: String = "0 Following"
)