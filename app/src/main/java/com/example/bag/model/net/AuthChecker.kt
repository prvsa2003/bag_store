package com.example.bag.model.net

import android.text.BoringLayout
import com.example.bag.model.data.LoginResponse
import com.example.bag.model.repository.TokenInMemory
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AuthChecker: Authenticator , KoinComponent {
    private val apiService: ApiService by inject()
    override fun authenticate(route: Route?, response: Response): Request? {
        if (TokenInMemory.token != null && !response.request.url.pathSegments.last()
                .equals("refreshToken", false)
        ) {
            val result = refreshtoken()
            if (result) {
                //moshkel az refresh bode
                //moshkel hal shodes!
                return response.request
            }

        }
        return null
    }


    private fun refreshtoken():Boolean{
        val request:retrofit2.Response<LoginResponse> = apiService.refreshtoken().execute()
        if (request.body()!=null){
            if(request.body()!!.Success){
                return true
            }
        }
            return false
    }
}