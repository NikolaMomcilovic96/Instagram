package com.raywenderlich.instagramclient

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.raywenderlich.instagramclient.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.createNewPostButton.setOnClickListener {
            sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
            val username = sharedPreferences.getString("LOGGED_USER", "").toString()
            val imageText = binding.postImage.text.toString()
            val desc = binding.postDescription.text.toString()
            var post = 0
            when (imageText) {
                "micko" -> post = R.drawable.micko
                "bolto" -> post = R.drawable.bolto
                "coolpost" -> post = R.drawable.coolpost
                "guts" -> post = R.drawable.guts
            }
            if (imageText.isNotEmpty()) {
                val newPost = Post(username, post, desc)
                posts.add(newPost)
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                Toast.makeText(this, "Photo is required", Toast.LENGTH_SHORT).show()
            }
        }
    }
}