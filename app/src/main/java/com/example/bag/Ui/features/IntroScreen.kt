package com.example.bag.Ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bag.R
import com.example.bag.Ui.DuniBazarUi
import com.example.bag.Ui.Theme.BackgroundMain
import com.example.bag.Ui.Theme.BagTheme
import com.example.bag.Ui.Theme.Blue
import com.example.bag.Util.MyScreens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController

@Composable
fun IntroScreen() {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(Blue)
    }
    val navigation = getNavController()



    Image(modifier = Modifier.fillMaxSize() ,
        painter = painterResource(id = R.drawable.img_intro) ,
        contentDescription = null ,
        contentScale = ContentScale.Crop

    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f) ,

        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button( modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(bottom = 0.dp)  ,

            onClick = {  navigation.navigate(MyScreens.SignUpScreen.route)}
        ) {
            Text(text ="signUp" , color = Color.White)
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(bottom = 220.dp), colors =ButtonDefaults.buttonColors(containerColor = Color.White),
            onClick = { navigation.navigate(MyScreens.SignInScreen.route)}
        ) {
            Text(
                text = "Sign In" , color = Blue
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BagTheme {

        Surface(
            color = BackgroundMain ,
            modifier = Modifier.fillMaxSize()
        ) {
            IntroScreen()
        }

    }
}