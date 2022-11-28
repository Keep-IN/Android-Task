package com.example.fragmentapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentapp.databinding.LayoutLoginBinding
import com.example.fragmentapp.databinding.LayoutRegisterBinding

class RegisterFragment: Fragment() {
    companion object {
        const val TAG = "RegisterFragment"
    }
    private lateinit var binding: LayoutRegisterBinding
    private var registerInterface: LoginFragmentInterface? = null

    override  fun onAttach(context: Context){
        super.onAttach(context)
        if (context is LoginFragmentInterface){
            registerInterface = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutRegisterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun typeToUsername(string: String){
        binding.userIdTextInput.editText?.setText(string)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toLogin.setOnClickListener {
            registerInterface?.onClickSetLogin()
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

//    interface FragmentListener {
//        fun onClickConfirm()
//    }
}