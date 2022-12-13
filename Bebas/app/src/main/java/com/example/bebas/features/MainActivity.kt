package com.example.bebas.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.bebas.databinding.ActivityMainBinding
import com.example.bebas.features.login.LoginPresenter
import com.example.bebas.features.login.LoginView
import com.example.bebas.features.signup.AccountActivate

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

        binding.usernameTextInput.editText?.doOnTextChanged { text, start, before, count ->
            onValidateInput()
        }

        binding.passwordTextInput.editText?.doOnTextChanged { text, start, before, count ->
            onValidateInput()
        }

    }

    private fun onValidateInput(){
        presenter.validateUser(
            txUsername,
            txPassword
        )
        binding.btnLogin.isEnabled = txUsername.isNotBlank() && txPassword.isNotBlank()
    }

    override fun onLoading() {
        binding.progressBar.isVisible = true
    }

    override fun onFinishLoading() {
        binding.progressBar.isVisible = false
    }

    override fun onError(code: Int, message: String) {
        when(code){
            0 -> binding.usernameTextInput.error = message
            1 -> binding.passwordTextInput.error = message
        }
    }

    override fun onErrorUsername(message: String) {

    }

    override fun onErrorPassword(message: String) {

    }

    override fun onSuccesUsername() {

    }

    override fun onSuccesPassword() {

    }

    override fun onSuccessLogin() {
        binding.usernameTextInput.error = null
        binding.passwordTextInput.error = null
        Toast.makeText(this, "Success Login", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}