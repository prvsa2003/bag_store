package com.example.bag.model.repository.User

interface UserRepository {
    //online
    suspend fun SinUp(name : String , username : String , password : String):String
    suspend fun SinIn(username : String , password : String):String

    //offline
    fun SignOut()
    fun loadToken()


    fun SaaveToken(newtoken : String)
    fun gettoken () : String


    fun saveuserName(username: String)
    fun getUserName():String


}