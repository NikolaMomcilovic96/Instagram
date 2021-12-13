package com.raywenderlich.instagramclient.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.raywenderlich.instagramclient.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun loadAll(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE id = :userId")
    fun loadUser(userId: Int): Int

    @Query("SELECT profilePicture FROM User WHERE id = :userId")
    fun loadProfilePicture(userId: Int): Int

    @Query("SELECT * FROM User WHERE username = :username")
    fun loadUser(username: String): User

    @Insert(onConflict = IGNORE)
    fun insertUser(user: User)
}