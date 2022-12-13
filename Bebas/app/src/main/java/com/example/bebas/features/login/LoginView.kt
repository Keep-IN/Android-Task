package com.example.bebas.features.login

interface LoginView {
    fun onLoading()
    fun onFinishLoading()
    fun onError(code:Int, message: String)
    fun onErrorUsername(message: String)
    fun onErrorPassword(message: String)
    fun onSuccesUsername()
    fun onSuccesPassword()
    fun onSuccessLogin()
}