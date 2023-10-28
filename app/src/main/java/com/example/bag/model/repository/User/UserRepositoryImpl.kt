package com.example.bag.model.repository.User

import android.content.SharedPreferences
import com.example.bag.Util.VALUE_SUCCSES
import com.example.bag.model.net.ApiService
import com.example.bag.model.repository.TokenInMemory
import com.google.gson.JsonObject

class UserRepositoryImpl(
    private val apiservice: ApiService,
    private val sharedPreferences: SharedPreferences
) : UserRepository {
    override suspend fun SinUp(name: String, username: String, password: String): String {
        val jsonObject = JsonObject().apply {
            addProperty("name", name)
            addProperty("email", username)
            addProperty("password", password)

        }

        val result = apiservice.signUp(jsonObject)
        if (result.Success) {

            TokenInMemory.refreshtoken(username, result.token)
            SaaveToken(result.token)
            saveuserName(username)

            return VALUE_SUCCSES
        } else {
            return result.message
        }


    }

    override suspend fun SinIn(username: String, password: String): String {
        val jsonObject = JsonObject().apply {
            addProperty("email", username)
            addProperty("password", password)
        }

        val result = apiservice.SignIn(jsonObject)
        if (result.Success) {
            TokenInMemory.refreshtoken(username, result.token)
            SaaveToken(result.token)
            saveuserName(username)
            return VALUE_SUCCSES
        } else {
            return result.message
        }

    }

    override fun SignOut() {

        TokenInMemory.refreshtoken(null, null)
        sharedPreferences.edit().clear().apply()

    }

    override fun loadToken() {
        TokenInMemory.refreshtoken(getUserName(), gettoken())
    }

    override fun SaaveToken(newtoken: String) {
        sharedPreferences.edit().putString("token", newtoken).apply()

    }

    override fun gettoken(): String {
        return sharedPreferences.getString("token", "")!!
    }

    override fun saveuserName(username: String) {
        sharedPreferences.edit().putString("username", username).apply()

    }

    override fun getUserName(): String {
        return sharedPreferences.getString("username", "")!!

    }
}