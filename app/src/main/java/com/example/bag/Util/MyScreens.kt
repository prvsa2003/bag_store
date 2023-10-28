package com.example.bag.Util

sealed class MyScreens(val route : String){
    object MainScreen : MyScreens("mainScreen")
    object ProductScreen : MyScreens("productScreen")
    object CategoryScreen : MyScreens("categoryScreen")
    object ProfileScreen : MyScreens("profileScreen")
    object CartScreen : MyScreens("cartScreen")
    object SignUpScreen : MyScreens("signupScreen")
    object SignInScreen : MyScreens("signInScreen")
    object IntroScreen : MyScreens("introScreen")
    object NoInternetScrees : MyScreens("noInternerScreen")



}
