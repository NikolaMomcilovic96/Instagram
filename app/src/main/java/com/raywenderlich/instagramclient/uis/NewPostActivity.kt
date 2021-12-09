package com.raywenderlich.instagramclient.uis

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.raywenderlich.instagramclient.R
import com.raywenderlich.instagramclient.databinding.ActivityNewPostBinding
import com.raywenderlich.instagramclient.db.PostDatabase
import com.raywenderlich.instagramclient.model.Post
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.createNewPostButton.setOnClickListener {
            insertPost()
        }
    }

    private fun insertPost() {
        sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("LOGGED_USER", "").toString()
        val imageText = binding.postImage.text.toString()
        val desc = binding.postDescription.text.toString()
        var image = 0
        when (imageText) {
            "micko" -> image = R.drawable.micko
            "bolto" -> image = R.drawable.bolto
            "coolpost" -> image = R.drawable.coolpost
            "guts" -> image = R.drawable.guts
        }
        if (imageText.isNotEmpty()) {

            val dbInstance = PostDatabase.getPostDatabase(this)

            GlobalScope.launch {
                val newPost = Post(0, username, image, desc, 0)
                dbInstance?.postDao()?.insertPost(newPost)
            }
            Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Photo is required", Toast.LENGTH_SHORT).show()
        }
    }
}