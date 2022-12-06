package com.example.restfulapi.network.api

import com.example.restfulapi.MoshiExtension
import com.example.restfulapi.deserializeJson
import com.example.restfulapi.network.Mapper.toUserPagination
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
    fun getUser(onResponse: (ResponseStatus<User>) -> Unit) {
        NetClient
            .client
            .newCall(
                NetClient.requestBuilder(usersEndpoint)
            )
            .enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(ResponseStatus.Failed(1, e.message.toString(), e))
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val body = JSONObject(response.body?.string() ?: "")
                        onResponse.invoke(
                            ResponseStatus.Success(User(body))
                        )
                    } else {
                        onResponse.invoke(
                            ResponseStatus.Failed(response.code, "Failed")
                        )
                    }
                }
            })
    }

    fun getUserPagination(pages: Int = 2, onResponse: (ResponseStatus<List<User>>) -> Unit) {
        val endpoint = "$usersEndpoint${if (pages > 2) "?page=$pages" else ""}"
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
                        val adapter = MoshiExtension.moshi.adapter(UserPagination::class.java)
                        val dataPagination: UserPagination = adapter.fromJson(response.body?.string() ?: "") ?: UserPagination()
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

    fun getError(onResponse: (ResponseStatus<Nothing>) -> Unit) {
        NetClient
            .client
            .newCall(NetClient.requestBuilder("/unknown/23"))
            .enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    onResponse.invoke(ResponseStatus.Failed(-1, e.message.toString(), e))
                }

                override fun onResponse(call: Call, response: Response) {
                    onResponse.invoke(ResponseStatus.Failed(-1, response.message))
                    response.body?.close()
                }
            })
    }

    private fun mapUsers(jsonObject: JSONObject): List<User> {
        val usersArray = jsonObject.getJSONArray("data")
        val resultData = mutableListOf<User>()
        for (i in 0 until usersArray.length()) {
            val obj = usersArray.getJSONObject(i)
            resultData.add(User(obj))
        }
        return resultData
    }
}