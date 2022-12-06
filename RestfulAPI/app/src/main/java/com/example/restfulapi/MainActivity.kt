package com.example.restfulapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.restfulapi.adapter.UserAdapter
import com.example.restfulapi.databinding.ActivityMainBinding
import com.example.restfulapi.network.NetClient
import com.example.restfulapi.network.ResponseStatus
import com.example.restfulapi.network.api.UserApi
import com.example.restfulapi.network.model.User
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterNotif: UserAdapter
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            UserApi().getUserPagination {
                scope.launch {
                    when(it){
                        is ResponseStatus.Success -> {
                            adapterNotif = UserAdapter(this@MainActivity, it.data.toMutableList())
                            binding.rvUsersList.adapter = adapterNotif
                            binding.rvUsersList.layoutManager = layoutManager
                            adapterNotif.setOnItemClicker(rvClickListener)
                        }
                        is ResponseStatus.Failed -> {
                            AlertDialog
                                .Builder(this@MainActivity)
                                .setMessage(it.message)
                                .create()
                                .show()
                        }
                        else -> {}
                    }
                }
            }
//            UserApi().getUsers { userPagination, error ->
//                CoroutineScope(Dispatchers.Main).launch {
//                    if (error == null) {
//                        adapterNotif = UserAdapter(this@MainActivity, userPagination?.data?.toMutableList() ?: mutableListOf())
//                        binding.rvUsersList.adapter = adapterNotif
//                        binding.rvUsersList.layoutManager = layoutManager
//                        adapterNotif.setOnItemClicker(rvClickListener)
//                    } else {
//                        AlertDialog
//                            .Builder(this@MainActivity)
//                            .setMessage(error.message)
//                            .create().show()
//                    }
//                }
//            }
        }
    }
    private val rvClickListener: (String, String, String) -> Unit =
        {name, email, avatar ->
            startActivity(Intent(this@MainActivity, UserDetail::class.java).apply {
                putExtra("userName", name)
                putExtra("userEmail", email)
                putExtra("userAvatar", avatar)
            })

        }
}