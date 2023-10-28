package com.example.bag.model.net

import com.example.bag.model.data.LoginResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("signUp")
    suspend fun signUp(@Body jsonObject: JsonObject): LoginResponse

    @POST("signIn")
    suspend fun SignIn(@Body jsonObject: JsonObject): LoginResponse
}