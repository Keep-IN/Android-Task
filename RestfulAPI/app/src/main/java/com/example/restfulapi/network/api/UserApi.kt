package com.example.restfulapi.network.api

import com.example.restfulapi.MoshiExtension
import com.example.restfulapi.deserializeJson
import com.example.restfulapi.network.NetClient
import com.example.restfulapi.network.ResponseStatus
import com.example.restfulapi.network.model.User
import com.example.restfulapi.network.model.UserPagination
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class UserApi {
    private val usersEndpoint = "/users"

    fun getUserPagination(pages: Int = 1, onResponse: (ResponseStatus<List<User>>) -> Unit) {
        val endpoint = "$usersEndpoint${if (pages > 1) "?page=$pages" else ""}"
        val request = NetClient.requestBuilder(endpoint)
        NetClient
            .client
            .newCall(request)
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(
                        ResponseStatus.Failed(
                            code = -1,
                            message = e.message.toString(),
                            throwable = e
                        )
                    )
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val userPagination = deserializeJson<UserPagination>(response.body?.string() ?: "") ?: UserPagination()
                        onResponse.invoke(
                            ResponseStatus.Success(
                                data = userPagination.data,
                                method = "GET",
                                status = true
                            )
                        )
                    } else {
                        onResponse.invoke(
                            ResponseStatus.Failed(response.code, "Failed")
                        )
                    }
                    response.body?.close()
                }
            })
    }

}