package com.example.fragmentapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.fragmentapp.databinding.LayoutLoginBinding

class LoginFragment: Fragment() {
    companion object{
        const val TAG = "LoginFragment"

        fun newInstance(data: String): LoginFragment{
            val fragment = LoginFragment()
            val bundle = Bundle().apply{
                putString("key", data)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
    private lateinit var binding: LayoutLoginBinding
    private var loginInterface: LoginFragmentInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginFragmentInterface){
            loginInterface = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun typeToUsername(string: String){
        binding.usernameTextInput.editText?.setText(string)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            loginInterface?.onClickLogin()
        }

        binding.forgotPass.setOnClickListener {
            loginInterface?.onClickForgot()
        }
    }
}