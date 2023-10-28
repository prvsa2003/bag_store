package com.example.bag.Ui.features.signIn

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bag.model.repository.User.UserRepository

class SignInViewModel(private val userRepository: UserRepository) :ViewModel(){

    val email = MutableLiveData("")
    val password = MutableLiveData("")



    fun SignInUser(){


    }

}