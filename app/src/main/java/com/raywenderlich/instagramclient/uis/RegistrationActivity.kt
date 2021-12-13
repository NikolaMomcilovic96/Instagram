package com.raywenderlich.instagramclient.uis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.raywenderlich.instagramclient.databinding.ActivityRegistrationBinding
import com.raywenderlich.instagramclient.model.User
import com.raywenderlich.instagramclient.viewmodel.UserViewModel

class RegistrationActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.registrationButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmation = binding.passwordConfirmEditText.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirmation.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                if (password == confirmation) {
                    val user = User(0, username, password,0,0,0,0)
                    userViewModel.addUser(user)
                    Toast.makeText(
                        this,
                        "Account created successfully. Please log in.",
                        Toast.LENGTH_LONG
                    ).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}