package com.example.restfulapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.restfulapi.databinding.ActivityUserDetailBinding

class UserDetail : AppCompatActivity() {
    private  lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAvatar = intent.getStringExtra("userAvatar")
        val userName = intent.getStringExtra("userName")
        val userEmail = intent.getStringExtra("userEmail")

        Glide.with(this).load(userAvatar).into(binding.userImage)
        binding.userName.text = userName
        binding.userEmail.text = userEmail

    }
}