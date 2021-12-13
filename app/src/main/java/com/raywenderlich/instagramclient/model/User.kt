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
    var posts: Int = 0,
    var followers: Int = 0,
    var following: Int = 0
)