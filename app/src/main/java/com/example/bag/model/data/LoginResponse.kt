package com.example.bag.model.data

data class LoginResponse(

    val expiresAt: Int,
    val message: String,
    val Success: Boolean,
    val token: String
)
