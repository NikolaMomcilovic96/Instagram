package com.raywenderlich.instagramclient.uis

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import androidx.lifecycle.ViewModelProvider
import com.raywenderlich.instagramclient.databinding.ActivityNewPostBinding
import com.raywenderlich.instagramclient.model.Post
import com.raywenderlich.instagramclient.viewmodel.PostViewModel
import java.io.File

class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewPostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        postViewModel = ViewModelProvider(this)[PostViewModel::class.java]

        binding.cameraButton.setOnClickListener {
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            filePhoto = getPhotoFile(FILE_NAME)
            val providerFile = FileProvider.getUriForFile(
                this,
                "com.raywenderlich.androidcamera.fileprovider",
                filePhoto
            )
            takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
            if (takePhotoIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePhotoIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Camera could not open", Toast.LENGTH_SHORT).show()
            }
        }

        binding.galleryButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else{
                    chooseImageGallery();

                }
            }else{
                chooseImageGallery();
            }
        }

        binding.createNewPostButton.setOnClickListener {
            insertPost()
        }
    }

    private fun chooseImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_CHOOSE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    chooseImageGallery()
                }else{
                    Toast.makeText(this,"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenPhoto = BitmapFactory.decodeFile(filePhoto.absolutePath)
            binding.viewImage.setImageBitmap(takenPhoto)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            binding.viewImage.setImageURI(data?.data)
        }
    }

    private fun getPhotoFile(fileName: String): File {
        val directoryStorage = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", directoryStorage)
    }

    private fun insertPost() {
        sharedPreferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("LOGGED_USER", "").toString()
        val desc = binding.postDescription.text.toString()
        val image = binding.viewImage

        val post = Post(0, username, image.drawToBitmap(), desc, 0)
        postViewModel.addPost(post)
        Toast.makeText(this, "Post created successfully!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
    }

    companion object {
        private const val FILE_NAME = "photo.jpg"
        private const val REQUEST_CODE = 1
        private lateinit var filePhoto: File
        private const val IMAGE_CHOOSE = 1000
        private const val PERMISSION_CODE = 1001
    }
}