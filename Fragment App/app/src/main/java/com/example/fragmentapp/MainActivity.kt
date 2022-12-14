package com.example.fragmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.fragmentapp.RegisterFragment.Companion.newInstanceRegist
import com.example.fragmentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginFragmentInterface {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, LoginFragment(), LoginFragment.TAG)
            .commit()
    }

    override fun onClickLogin() {
//        getLoginFragment()?.typeToUsername("Budi")
        Toast.makeText(this, "Ok", Toast.LENGTH_SHORT).show()
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, RegisterFragment(), RegisterFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onClickForgot() {
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, RegisterFragment(), RegisterFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun onClickSetLogin() {
        getLoginFragment()?.typeToUsername(getRegisFragment()?.typeToUsername().toString())
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, LoginFragment(), LoginFragment.TAG)
            .disallowAddToBackStack()
            .commit()
    }

    private fun getLoginFragment(): LoginFragment? {
        val fragment = supportFragmentManager.findFragmentByTag(LoginFragment.TAG)
        return if (fragment != null){
            fragment as LoginFragment
        } else{
            null
        }
    }

    private fun getRegisFragment(): RegisterFragment? {
        val fragment = supportFragmentManager.findFragmentByTag(RegisterFragment.TAG)
        return if (fragment != null){
            fragment as RegisterFragment
        } else{
            null
        }
    }
}