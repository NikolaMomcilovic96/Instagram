package com.raywenderlich.instagramclient.repository

import androidx.lifecycle.LiveData
import com.raywenderlich.instagramclient.db.PostDao
import com.raywenderlich.instagramclient.model.Post

class PostRepo(private val postDao: PostDao) {
    val readAllData:LiveData<List<Post>> = postDao.loadAll()

    fun addPost(post: Post){
        postDao.insertPost(post)
    }
}