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
import com.example.restfulapi.network.api.UserApi
import com.example.restfulapi.network.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterNotif: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            UserApi().getUsers { userPagination, error ->
                CoroutineScope(Dispatchers.Main).launch {
                    if (error == null) {
                        adapterNotif = UserAdapter(this@MainActivity, userPagination?.data?.toMutableList() ?: mutableListOf())
                        binding.rvUsersList.adapter = adapterNotif
                        binding.rvUsersList.layoutManager = layoutManager
                        adapterNotif.setOnItemClicker(rvClickListener)
                    } else {
                        AlertDialog
                            .Builder(this@MainActivity)
                            .setMessage(error.message)
                            .create().show()
                    }
                }
            }
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

//        val client = OkHttpClient()
//        val request = Request.Builder()
//            .url("https://reqres.in/api/users?page=1")
//            .build()
//        binding.button2.setOnClickListener {
//            client
//                .newCall(request)
//                .enqueue(object : Callback{
//                    override fun onFailure(call: Call, e: IOException) {
//
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        if(response.isSuccessful){
//                            val responseBody = JSONObject(response.body?.string() ?: "")
//                            val data = responseBody.getJSONArray("data")
//                            var emails = ""
//                            for (i in 0 until data.length()){
//                                val jsonUser = JSONObject(data.getString(i))
//                                emails += "${jsonUser["email"]}\n"
//                            }
//                            CoroutineScope(Dispatchers.Main).launch {
//                                binding.tvResult.text = emails
//                            }
//                        }
//                        response.close()
//                    }
//                })
//        }
//
//        val requestBody = JSONObject()
//        requestBody.put("name", "Brody")
//        requestBody.put("job", "Supervisor")
//
//        val requestPost = requestPost("/users", requestBody)
//
//        binding.button.setOnClickListener {
//            client
//                .newCall(requestPost)
//                .enqueue(object : Callback{
//                    override fun onFailure(call: Call, e: IOException) {
//
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        if(response.isSuccessful){
//                            val responseBody = JSONObject(response.body?.string() ?: "")
//                            Log.d("OkhttpClient", "responseBody: $responseBody")
//                        }
//                        response.close()
//                    }
//                })
//        }

//    private fun requestPost(endpoint: String, body: JSONObject): Request {
//        return Request.Builder()
//            .url("https://reqres.in/api/$endpoint")
//            .method("POST", body.toString().toRequestBody())
//            .addHeader("Content=Type", "application/json")
//            .build()
//    }
}