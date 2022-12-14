package com.example.brokenapp.features.signup

class SignupPresenter {
    private var view: SinupContract? = null

    fun onAttach(view: SinupContract) {
        this.view = view
    }

    fun onDetach(){
        this.view = null
    }



}