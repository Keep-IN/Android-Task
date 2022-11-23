package com.example.bebas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.bebas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val txUsername: String get() = binding.usernameTextInput.editText?.text.toString()
    private val txPassword: String get() = binding.passwordTextInput.editText?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.getStringExtra("idUser")?.isNotBlank() == true){
            binding.userCard.isVisible = true
            binding.nameOfuser.text = intent.getStringExtra("idUser")
            binding.rekNum.text = intent.getStringExtra("noRek")
        } else{
            binding.userCard.isVisible = false
        }

        binding.closeCardBtn.setOnClickListener{
            binding.userCard.isVisible = false
        }

        binding.forgotPass.setOnClickListener {
            startActivity(Intent(this@MainActivity, AccountActivate::class.java))
        }

        binding.usernameTextInput.editText?.doOnTextChanged { text, start, before, count ->
            validate()
        }

//        binding.userCard.isVisible = false

        binding.passwordTextInput.editText?.doOnTextChanged { text, start, before, count ->
            if(isPassValid){
                validate()
                binding.passwordTextInput.error = null
            }
            else{
                binding.passwordTextInput.error = "Password is too weak"
            }
        }
    }

    private fun validate() {
        binding.btnLogin.isEnabled =
            txUsername.isNotBlank() && txPassword.isNotBlank() && isPassValid

    }

    private val isPassValid: Boolean get(){
        val passText = txPassword

        val isValid = passText.contains("[a-z]".toRegex())
                && passText.contains("[A-Z]".toRegex())
                && passText.contains("[0-9]".toRegex())
                && passText.contains("[!@#$%^&*()_+=?/]".toRegex())
                && passText.length >= 8

        return isValid
    }
}