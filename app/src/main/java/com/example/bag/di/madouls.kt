package com.example.bag.di


import android.content.Context
import com.example.bag.Ui.features.signIn.SignInViewModel
import com.example.bag.Ui.features.signUp.SignUpViewModel
import com.example.bag.model.net.createapiservuce
import com.example.bag.model.repository.User.UserRepository
import com.example.bag.model.repository.User.UserRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myMadouls = module {
    single { androidContext().getSharedPreferences("data" , Context.MODE_PRIVATE) }
    single {  createapiservuce() }
    single <UserRepository>{ UserRepositoryImpl(get() ,get() ) }

    viewModel {SignUpViewModel(get())}
    viewModel { SignInViewModel(get () ) }
}