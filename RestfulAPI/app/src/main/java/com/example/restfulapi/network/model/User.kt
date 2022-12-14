package com.example.restfulapi.network.model

data class User(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
)