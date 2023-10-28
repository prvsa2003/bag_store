package com.example.bag.model.repository.User

import android.content.SharedPreferences
import com.example.bag.model.net.ApiService
import com.google.gson.JsonObject

class UserRepositoryImpl(
    private val apiservice : ApiService ,
    private val sharedPreferences: SharedPreferences
): UserRepository {
    override suspend fun SinUp(name: String, username: String, password: String):String {
        val jsonObject = JsonObject().apply {
            addProperty("name" , name)
            addProperty("email" , username)
            addProperty("password" , password)

        }

        val result = apiservice.signUp(jsonObject)
        if(result.Success){
            return
        }else{
            return result.message
        }


    }

    override suspend fun SinIn(username: String, password: String):String {

    }

    override fun SignOut() {

    }

    override fun loadToken() {

    }

    override fun SaaveToken(newtoken: String) {

    }

    override fun gettoken(): String {

    }

    override fun saveuserName(username: String) {

    }

    override fun getUserName(): String {

    }
}