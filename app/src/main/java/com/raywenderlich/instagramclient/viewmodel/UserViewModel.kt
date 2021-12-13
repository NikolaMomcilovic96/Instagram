package com.raywenderlich.instagramclient.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.raywenderlich.instagramclient.db.UserDatabase
import com.raywenderlich.instagramclient.model.User
import com.raywenderlich.instagramclient.repository.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val repository: UserRepo

    init {
        val userDao = UserDatabase.getUserDatabase(application)!!.userDao()
        repository = UserRepo(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun loadPicture(userId: Int): Int {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadPicture(userId)
        }
        return 1
    }

    fun loadUser(username: String): User {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadUser(username)
        }
        return User(0,username,"")
    }
}
