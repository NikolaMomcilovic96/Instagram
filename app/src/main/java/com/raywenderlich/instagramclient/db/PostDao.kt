package com.raywenderlich.instagramclient.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import com.raywenderlich.instagramclient.model.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM Post")
    fun loadAll(): LiveData<List<Post>>

    @Insert(onConflict = IGNORE)
    fun insertPost(post: Post): Long

    @Update(onConflict = REPLACE)
    fun updatePost(post: Post)

    @Delete
    fun deletePost(post: Post)
}