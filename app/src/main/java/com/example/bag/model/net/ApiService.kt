package com.example.bag.model.net

import com.example.bag.Util.BASE_URL
import com.example.bag.model.data.LoginResponse
import com.example.bag.model.repository.TokenInMemory
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("signUp")
    suspend fun signUp(@Body jsonObject: JsonObject): LoginResponse

    @POST("signIn")
    suspend fun SignIn(@Body jsonObject: JsonObject): LoginResponse

    @GET("refreshToken")
    fun refreshtoken(): Call<LoginResponse>
}

fun createapiservuce():ApiService{
    val okhttpclient = OkHttpClient.Builder()
        .addInterceptor{
            val oldrequest = it.request()
            val newrequestt = oldrequest.newBuilder()

            if(TokenInMemory.token!=null)
                newrequestt.addHeader("Authorization" , TokenInMemory.token!!)
            newrequestt.addHeader("Accept" , "application/json")
            newrequestt.method(oldrequest.method , oldrequest.body)

            return@addInterceptor it.proceed(newrequestt.build())


        }.build()


    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttpclient)
        .build()

    return retrofit.create(ApiService::class.java)

}