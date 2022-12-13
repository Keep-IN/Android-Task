package com.example.bebas.features.login

class LoginPresenter {
    companion object{

    }
    private var view: LoginView? = null

    fun onAttach(view: LoginView) {
        this.view = view
    }

    fun onDetach(){
        this.view = null
    }

    fun validateUser(userName: String, password: String){
        view?.onLoading()
        val isPasswordValid = password.contains("[a-z]".toRegex())
                && password.contains("[A-Z]".toRegex())
                && password.contains("[0-9]".toRegex())
                && password.length >= 8

        val isUsernameValid = userName.length > 8

        if (isPasswordValid && isUsernameValid) {
            view?.onSuccessLogin()
        } else if (!isUsernameValid) {
            view?.onError(0, "invalid username")
        } else {
            view?.onError(1, "invalid password")
        }

        view?.onFinishLoading()
    }
}