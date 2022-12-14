package com.example.brokenapp.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.brokenapp.R
import com.example.brokenapp.data.UserData
import com.example.brokenapp.databinding.ActivityMainBinding
import com.example.brokenapp.features.home.Home
import com.example.brokenapp.features.login.LoginPresenter
import com.example.brokenapp.features.login.LoginView
import com.example.brokenapp.features.signup.AccountActivate

class MainActivity : AppCompatActivity(), LoginView {
    private lateinit var binding: ActivityMainBinding
    private val presenter = LoginPresenter()
    private val txUsername: String get() = binding.usernameTextInput.editText?.text.toString()
    private val txPassword: String get() = binding.passwordTextInput.editText?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onAttach(this)

        binding.userCard.apply {
            setTextColor(R.color.white, R.color.white)
        }

        binding.usernameTextInput.editText?.doOnTextChanged { text, start, before, count ->
            presenter.validateUserName(txUsername)
            onValidateInput()
        }

        binding.passwordTextInput.editText?.doOnTextChanged { text, start, before, count ->
            presenter.validatePassword(txPassword)
            onValidateInput()
        }

        binding.btnLogin.setOnClickListener {
            presenter.validateUser()
            startActivity(Intent(this@MainActivity, Home::class.java))
        }

        binding.userCard.setListener(object : UserCardInterface {
            override fun onClickIcon() {
                binding.userCard.isVisible = false
            }
        })

        binding.forgotPass.setOnClickListener {
            startActivity(Intent(this@MainActivity, AccountActivate::class.java))
        }

    }

    private fun onValidateInput(){
        binding.btnLogin.isEnabled = txUsername.isNotBlank() && txPassword.isNotBlank()
                && presenter.validateUserName(txUsername)
                && presenter.validatePassword(txPassword)
    }

    override fun onLoading() {
        binding.progressBar.isVisible = true
    }

    override fun onFinishLoading() {
        binding.progressBar.isVisible = false
    }

    override fun onError(code: Int, message: String?) {
        when(code){
            0 -> binding.passwordTextInput.error = message
            1 -> binding.usernameTextInput.error = message
            3 -> binding.passwordTextInput.error = message
            4 -> binding.usernameTextInput.error = message
        }
    }

    override fun onSuccessLogin() {
        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}