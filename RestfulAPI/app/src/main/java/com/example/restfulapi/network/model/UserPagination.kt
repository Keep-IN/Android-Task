package com.example.restfulapi.network.model

import com.example.restfulapi.network.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserPagination(
    val page: Int = 0,
    @Json(name = "per_page")
    val perPage: Int = 0,
    val total: Int = 0,
    @Json(name = "total_page")
    val totalPage: Int = 0,
    val data: List<User> = listOf()
)