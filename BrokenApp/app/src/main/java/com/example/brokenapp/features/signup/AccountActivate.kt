package com.example.brokenapp.features.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.brokenapp.databinding.ActivityAccountActivateBinding
import com.example.brokenapp.features.MainActivity
import com.example.brokenapp.features.home.HomeContract

class AccountActivate : AppCompatActivity(), SinupContract {
    private lateinit var binding: ActivityAccountActivateBinding
    private val presenter = SignupPresenter()
    private val txIdUser: String get() = binding.userIdTextInput.editText?.text.toString()
    private val txNorek: String get() = binding.noRekTextInput.editText?.text.toString()
    private val txPassword: String get() = binding.passwordInput.editText?.text.toString()
    private val txConPassword: String get() = binding.confirmPassInput.editText?.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountActivateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.onAttach(this)

        binding.backButton.setOnClickListener {
            startActivity(Intent(this@AccountActivate, MainActivity::class.java))
        }

        binding.toLogin.setOnClickListener {

        }
    }

    override fun onLoading() {
        binding.progressBar2.isVisible = true
    }

    override fun onFinishedLoading() {
        binding.progressBar2.isVisible = false
    }

    override fun onError(code: Int, message: String) {

    }

    override fun onSuccesRegister() {

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}