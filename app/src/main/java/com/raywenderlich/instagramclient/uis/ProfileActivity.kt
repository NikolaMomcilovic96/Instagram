package com.raywenderlich.instagramclient.uis

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.raywenderlich.instagramclient.R
import com.raywenderlich.instagramclient.databinding.ActivityProfileBinding
import com.raywenderlich.instagramclient.viewmodel.UserViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        sharedPreferences =
            getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("LOGGED_USER", "").toString()

        val user = userViewModel.loadUser(username)

        binding.profileUsernameTextView.text = user.username
        binding.profilePictureImageView.setImageResource(user.profilePicture)
        binding.postsTextView.text = user.posts.toString()
        binding.followersTextView.text = user.followers.toString()
        binding.followingTextView.text = user.following.toString()

        binding.profilePictureImageView.setOnClickListener {
            startActivity(Intent(this, ChangeProfilePictureActivity::class.java))
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