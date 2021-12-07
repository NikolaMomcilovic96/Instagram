package com.raywenderlich.instagramclient.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.raywenderlich.instagramclient.model.User

@Dao
interface UserDao {
    @Insert(onConflict = IGNORE)
    fun insertUser(user: User): Long

    @Query("SELECT * FROM User WHERE id = :userId")
    fun loadUser(userId: Long): User
}