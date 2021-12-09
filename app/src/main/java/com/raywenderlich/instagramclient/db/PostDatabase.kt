package com.raywenderlich.instagramclient.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.raywenderlich.instagramclient.model.Post

@Database(entities = [Post::class], version = 1)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        private var INSTANCE: PostDatabase? = null

        fun getPostDatabase(context: Context): PostDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java,
                    "Post"
                )
                    .build()
            }
            return INSTANCE
        }

        fun cleanObject() {
            INSTANCE = null
        }
    }
}