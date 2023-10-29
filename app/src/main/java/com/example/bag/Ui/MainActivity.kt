package com.example.bag.Ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bag.Ui.Theme.BackgroundMain
import com.example.bag.Ui.Theme.BagTheme
import com.example.bag.Ui.features.IntroScreen
import com.example.bag.Ui.features.signIn.SignInScreen
import com.example.bag.Ui.features.signUp.SignUpScreen
import com.example.bag.Util.KEY_CATEGORY_ARG
import com.example.bag.Util.KEY_PRODUCT_ARG
import com.example.bag.Util.MyScreens
import com.example.bag.di.myMadouls
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myMadouls )

            }){
                BagTheme {
                    Surface(
                        color = BackgroundMain ,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        DuniBazarUi()
                    }
                }
            }

        }
    }
}


@Composable
fun DuniBazarUi() {
    val navcontroller = rememberNavController()
    KoinNavHost(
        navController = navcontroller,
        startDestination = MyScreens.IntroScreen.route
    ) {
        composable(MyScreens.MainScreen.route) {
            MainScreen()
        }

        composable(
            route = MyScreens.ProductScreen.route+"/" + KEY_PRODUCT_ARG ,
            arguments = listOf(navArgument(KEY_PRODUCT_ARG) {
                type = NavType.IntType
            })
        ) {

            ProductScreen(it.arguments!!.getInt(KEY_PRODUCT_ARG, -1))
        }
        composable(MyScreens.CategoryScreen.route+"/" + KEY_CATEGORY_ARG , arguments = listOf(navArgument(KEY_CATEGORY_ARG) {
            type = NavType.StringType
        })) {
            CategoryScreen(it.arguments!!.getString(KEY_CATEGORY_ARG, null))
        }
        composable(MyScreens.ProfileScreen.route) {
            ProfileScreen()
        }
        composable(MyScreens.CartScreen.route) {
            CartScreen()
        }

        composable(MyScreens.SignUpScreen.route) {
            SignUpScreen()
        }
        composable(MyScreens.SignInScreen.route) {
            SignInScreen()
        }
        composable(MyScreens.IntroScreen.route) {
            IntroScreen()
        }
        composable(MyScreens.NoInternetScrees.route) {
            NoInternetScreen()
        }


    }
}

@Composable
fun NoInternetScreen() {

}






@Composable
fun CartScreen() {

}

@Composable
fun ProfileScreen() {

}

@Composable
fun CategoryScreen(categoryname: String) {

}

@Composable
fun ProductScreen(productid: Int) {

}

@Composable
fun MainScreen() {

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BagTheme {
        Surface(
            color = BackgroundMain ,
            modifier = Modifier.fillMaxSize()
        ) {
            DuniBazarUi()
        }

    }
}