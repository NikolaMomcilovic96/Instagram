package com.raywenderlich.instagramclient.uis

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.raywenderlich.instagramclient.R
import com.raywenderlich.instagramclient.databinding.ActivityProfileBinding
import com.raywenderlich.instagramclient.users

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences =
            getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("LOGGED_USER", "")
        for (user in users) {
            Log.d("USER", user.toString())
            if (user.username == username) {
                val picture = user.profilePicture
                val posts = user.posts
                val followers = user.followers
                val following = user.following

                binding.profileUsernameTextView.text = username
                binding.profilePictureImageView.setImageResource(picture)
                binding.postsTextView.text = posts
                binding.followersTextView.text = followers
                binding.followingTextView.text = following
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logoutItem) {
            val editor = sharedPreferences.edit()
            editor.putString("LOGGED_USER", "")
            editor.apply()
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        return true
    }
}