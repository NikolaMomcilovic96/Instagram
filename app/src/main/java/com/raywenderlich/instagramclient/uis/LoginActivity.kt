package com.raywenderlich.instagramclient.uis

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.raywenderlich.instagramclient.databinding.ActivityLoginBinding
import com.raywenderlich.instagramclient.users

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("LOGGED_USER", "")

        if (savedUsername.isNullOrEmpty()) {
            binding.loginButton.setOnClickListener {
                val username = binding.usernameEditText.text.toString()
                val password = binding.passwordEditText.text.toString()

                for (user in users){
                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                    } else if (username==user.username){
                        sharedPreferences.edit().putString("LOGGED_USER", username).apply()
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }

            binding.createAccountTextView.setOnClickListener {
                startActivity(Intent(this, RegistrationActivity::class.java))
            }
        } else {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}