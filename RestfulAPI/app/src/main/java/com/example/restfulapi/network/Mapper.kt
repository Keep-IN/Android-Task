package com.example.restfulapi.network

import com.example.restfulapi.network.model.User
import com.example.restfulapi.network.model.UserPagination
import org.json.JSONObject

object Mapper {
    fun JSONObject.toUserPagination(): UserPagination {
        val userData = this.getJSONArray("data")
        val users = mutableListOf<User>()
        for (i in 0 until userData.length()) {
            val json = userData.getJSONObject(i)
            users.add(json.toUser())
        }
        return UserPagination(
            page = this.getInt("page"),
            perPage = this.getInt("per_page"),
            total = this.getInt("total"),
            totalPage = this.getInt("total_pages"),
            data = users
        )
    }

    fun JSONObject.toUser(): User {
        return User(
            id = getInt("id"),
            email = getString("email"),
            firstName = getString("first_name"),
            lastName = getString("last_name"),
            avatar = getString("avatar")
        )
    }

    fun JSONObject.getStringData(key: String, default: String): String {
        return if (this.has(key)) {
            this.getString(key)
        } else {
            default
        }
    }

    fun JSONObject.getBooleanData(key: String, default: Boolean): Boolean {
        return if (this.has(key)) {
            this.getBoolean(key)
        } else {
            default
        }
    }

    fun JSONObject.getIntData(key: String): Int {
        return if (this.has(key)) {
            this.getInt(key)
        } else {
            0
        }
    }
}