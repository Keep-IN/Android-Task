package com.example.brokenapp.features.login

class LoginPresenter {
    private var view: LoginView? = null

    fun onAttach(view: LoginView) {
        this.view = view
    }

    fun onDetach(){
        this.view = null
    }

    private var isPasswordValid: Boolean = true
    private var isUserNameValid: Boolean = true


    fun validatePassword(password: String): Boolean{
        isPasswordValid = password.contains("[a-z]".toRegex())
                && password.contains("[A-Z]".toRegex())
                && password.contains("[0-9]".toRegex())
                && password.length >= 8

        if (!isPasswordValid){
            view?.onError(0, "invalid password")
        } else {
            view?.onError(3, null)
        }
        return isPasswordValid
    }

    fun validateUserName(userName: String): Boolean{
        isUserNameValid = userName.length > 8

        if (!isUserNameValid){
            view?.onError(1, "invalid username")
        } else{
            view?.onError(4, null)
        }
        return isUserNameValid
    }

    fun validateUser(){
        view?.onLoading()

        if (isPasswordValid && isUserNameValid) {
            view?.onSuccessLogin()
        }

        view?.onFinishLoading()
    }
}