package com.example.restfulapi.network

import com.example.restfulapi.BuildConfig
import com.example.restfulapi.MoshiExtension
import com.example.restfulapi.deserializeJson
import com.example.restfulapi.network.model.User
import com.example.restfulapi.network.model.UserPagination
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class NetClient {
    companion object {
        const val BASE_URL = "https://reqres.in/api"
        private val headerInterceptor: Interceptor = Interceptor {
            val request = it.request().newBuilder()
            request
                .addHeader("Content-Type", "application/json")

            return@Interceptor it.proceed(request.build())

        }

        val client: OkHttpClient by lazy {
            OkHttpClient
                .Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level =
                            if (androidx.viewbinding.BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                    }
                )
                .callTimeout(timeout = 5L, unit = TimeUnit.SECONDS)
                .connectTimeout(timeout = 2L, unit = TimeUnit.SECONDS)
                .build()
        }

        fun requestBuilder(endpoint: String, method: METHOD = METHOD.GET, jsonBody: String? = null): Request {
            val request = Request
                .Builder()
                .url("$BASE_URL$endpoint")

            if (jsonBody != null)
                request.method(method.name, jsonBody.toRequestBody())

            return request.build()
        }
    }
    enum class METHOD {
        GET,
        POST
    }

//        fun getApi(endpoint: String, body: JSONObject?, onResponse: (ResponseStatus<List<User>>) -> Unit) {
//            val getRequest = Request.Builder()
//                .url("$baseUrl$endpoint")
//            if (body != null) {
//                getRequest.method("GET", body.toString().toRequestBody())
//            }
//
//            getOkhttpClient()
//                .newCall(getRequest.build())
//                .enqueue(object : Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        onResponse.invoke(
//                            ResponseStatus.Failed(1, e.message.toString(),e)
//                        )
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        if (response.isSuccessful) {
//                            val userPagination = deserializeJson<UserPagination>(response.body?.string() ?: "") ?: UserPagination()
//                            val adapter = MoshiExtension.moshi.adapter(UserPagination::class.java)
////                            val responseBody = response.body?.string()?.let { JSONObject(it) }
//                            onResponse.invoke(
//                                ResponseStatus.Success(
//                                    data = userPagination,
//                                    method = "GET",
//                                    status = true
//                                )
//                            )
//                        } else {
//                            onResponse.invoke(
//                                ResponseStatus.Failed(response.code, "Failed")
//                            )
//                        }
//                        response.body?.close()
//                    }
//                })
//        }
//    }

}