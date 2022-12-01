package com.example.restfulapi.network.api

import com.example.restfulapi.network.Mapper.toUserPagination
import com.example.restfulapi.network.NetClient
import com.example.restfulapi.network.model.UserPagination

class UserApi {
    fun getUsers(page: Int = 1, onResponse: (UserPagination?, Throwable?) -> Unit) {
        NetClient
            .getApi("/users?page$page", null) { responseBody, error ->
                if (error == null) {
                    val userPagination: UserPagination = responseBody?.toUserPagination() ?: UserPagination()
                    onResponse.invoke(userPagination, null)
                } else {
                    onResponse.invoke(null, error)
                }
            }
    }
}