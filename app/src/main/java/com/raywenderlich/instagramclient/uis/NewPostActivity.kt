package com.raywenderlich.instagramclient.uis

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.raywenderlich.instagramclient.R
import com.raywenderlich.instagramclient.databinding.ActivityNewPostBinding
import com.raywenderlich.instagramclient.db.PostDatabase
import com.raywenderlich.instagramclient.model.Post
import com.raywenderlich.instagramclient.viewmodel.PostViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

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
            val post = Post(0, username, image, desc, 0)
            postViewModel.addPost(post)
            Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Photo is required", Toast.LENGTH_SHORT).show()
        }
    }
}