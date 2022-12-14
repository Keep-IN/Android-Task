package com.example.brokenapp.features.login

interface LoginView {
    fun onLoading()
    fun onFinishLoading()
    fun onError(code: Int, message: String?)
    fun onSuccessLogin()
}