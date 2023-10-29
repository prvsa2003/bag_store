package com.example.bag.Ui.features.signUp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bag.Ui.MainActivity
import com.example.bag.model.repository.User.UserRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmpassword = MutableLiveData("")


    fun SignUpUser(LoggingEvent: (String) -> Unit) {
        viewModelScope.launch {

            val result = userRepository.SinUp(name.value!!, email.value!!, password.value!!)
            LoggingEvent(result)


        }


    }

}