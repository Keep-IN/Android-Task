package com.example.brokenapp.features.signup

interface SinupContract {
    fun onLoading()
    fun onFinishedLoading()
    fun onError(code: Int, message: String)
    fun onSuccesRegister()
}