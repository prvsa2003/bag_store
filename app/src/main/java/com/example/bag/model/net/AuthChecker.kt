package com.example.bag.model.net

import com.example.bag.model.repository.TokenInMemory
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AuthChecker: Authenticator , KoinComponent {
    private val apiService : ApiService by inject()
    override fun authenticate(route: Route?, response: Response): Request? {
        if(TokenInMemory.token!=null){

        }

    }

    private fun refreshtoken(){

    }
}