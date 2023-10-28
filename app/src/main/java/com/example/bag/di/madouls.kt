package com.example.bag.di


import com.example.bag.Ui.features.signIn.SignInViewModel
import com.example.bag.Ui.features.signUp.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myMadouls = module {

    viewModel {SignUpViewModel()}
    viewModel { SignInViewModel() }
}