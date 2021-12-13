package com.raywenderlich.instagramclient.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.raywenderlich.instagramclient.db.PostDatabase
import com.raywenderlich.instagramclient.model.Post
import com.raywenderlich.instagramclient.repository.PostRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Post>>
    private val repository: PostRepo

    init {
        val postDao = PostDatabase.getPostDatabase(application)!!.postDao()
        repository = PostRepo(postDao)
        readAllData = repository.readAllData
    }

    fun addPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPost(post)
        }
    }
}