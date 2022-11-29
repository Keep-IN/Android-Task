package com.example.brokenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brokenapp.databinding.ActivityMainBinding
import com.example.brokenapp.databinding.ActivityAccountActivateBinding

class AccountActivate : AppCompatActivity() {
    private lateinit var binding: ActivityAccountActivateBinding
    private val txIdUser: String get() = binding.userIdTextInput.editText?.text.toString()
    private val txNorek: String get() = binding.noRekTextInput.editText?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountActivateBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.userIdTextInput.editText?.setText(intent.getStringExtra("username"))

        binding.toLogin.setOnClickListener {
            startActivity(Intent(this@AccountActivate, MainActivity::class.java)
                .putExtra("idUser", txIdUser).putExtra("noRek", txNorek))
        }
    }
}