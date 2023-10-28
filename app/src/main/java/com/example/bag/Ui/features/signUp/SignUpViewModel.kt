package com.example.bag.Ui.features.signUp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bag.Ui.MainActivity
import kotlin.coroutines.coroutineContext

class SignUpViewModel :ViewModel(){
    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmpassword = MutableLiveData("")


    fun SignUpUser(){


    }

}