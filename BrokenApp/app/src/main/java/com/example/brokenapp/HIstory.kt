package com.example.brokenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brokenapp.databinding.ActivityHistoryBinding
import com.example.brokenapp.databinding.ActivityMainBinding

class HIstory : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val accTitle = intent.getStringExtra("accTitle")
        val accDetail = intent.getStringExtra("accDetail")
        val accAmount = intent.getStringExtra("accAmount")

        binding.tvTitleRecycle.text = "$accTitle"
        binding.tvAccDetail.text = "$accDetail"
        binding.tvAmount.text = "$accAmount"
    }
}