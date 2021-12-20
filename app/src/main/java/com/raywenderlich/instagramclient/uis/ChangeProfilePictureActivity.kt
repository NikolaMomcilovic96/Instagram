package com.raywenderlich.instagramclient.uis

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.raywenderlich.instagramclient.R
import com.raywenderlich.instagramclient.databinding.ActivityChangeProfilePictureBinding
import com.raywenderlich.instagramclient.model.User
import com.raywenderlich.instagramclient.viewmodel.UserViewModel

class ChangeProfilePictureActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityChangeProfilePictureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangeProfilePictureBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.updateButton.setOnClickListener {
            updateProfilePicture()
        }
    }

    private fun updateProfilePicture() {
        sharedPreferences =
            getSharedPreferences("sharedPref", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("LOGGED_USER", "").toString()

        val user = userViewModel.loadUser(username)
        val picture = binding.profilePictureEditText.text.toString()
        val newPicture = when (picture) {
            "micko" -> R.drawable.micko
            "bolto" -> R.drawable.bolto
            "coolpost" -> R.drawable.coolpost
            "guts" -> R.drawable.guts
            else -> R.drawable.bjelica
        }
        if (picture.isNotEmpty()) {
            val updatedUser = User(
                user.id,
                user.username,
                user.password,
                newPicture,
                user.posts,
                user.followers,
                user.following
            )

            userViewModel.updateUser(updatedUser)
            Toast.makeText(this, "Successfully updated user!", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(this, ProfileActivity::class.java))
        } else {
            Toast.makeText(this, "You didn't add any new picture", Toast.LENGTH_SHORT).show()
        }
    }
}