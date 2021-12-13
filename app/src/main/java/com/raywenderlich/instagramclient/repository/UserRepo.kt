package com.raywenderlich.instagramclient.repository

import androidx.lifecycle.LiveData
import com.raywenderlich.instagramclient.db.UserDao
import com.raywenderlich.instagramclient.model.User

class UserRepo(private val userDao: UserDao) {
    val readAllData: LiveData<List<User>> = userDao.loadAll()

    fun addUser(user: User) {
        userDao.insertUser(user)
    }

    fun loadPicture(userId: Int): Int {
        return userDao.loadProfilePicture(userId)
    }

    fun loadUser(username: String):User{
        return userDao.loadUser(username)
    }
}