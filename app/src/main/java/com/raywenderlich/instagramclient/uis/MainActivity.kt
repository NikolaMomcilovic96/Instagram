package com.raywenderlich.instagramclient.uis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.WindowCompat
import com.raywenderlich.instagramclient.R
import com.raywenderlich.instagramclient.databinding.MainActivityBinding
import com.raywenderlich.instagramclient.posts
import com.raywenderlich.instagramclient.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = MainActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        for (post in posts){
            Log.d("POST",post.toString())
        }

        binding.newPostButton.setOnClickListener {
            startActivity(Intent(this, NewPostActivity::class.java))
        }

        binding.profileButton.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}