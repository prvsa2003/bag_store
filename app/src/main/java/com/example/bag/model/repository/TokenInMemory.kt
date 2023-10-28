package com.example.bag.model.repository

object TokenInMemory {
    var username : String?=null
        private set

    var token : String ? = null
        private set


    fun refreshtoken(username:String? , newtoken : String?){
        this.username = username
        this.token = newtoken
    }
    

}