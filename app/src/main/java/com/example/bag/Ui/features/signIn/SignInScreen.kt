package com.example.bag.Ui.features.signIn

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bag.R
import com.example.bag.Ui.Theme.BackgroundMain
import com.example.bag.Ui.Theme.BagTheme
import com.example.bag.Ui.Theme.Blue
import com.example.bag.Ui.Theme.Shapes
import com.example.bag.Util.MyScreens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import ir.dunijet.broadcastreceiver.NetworkChecker

@Preview(showBackground = true)
@Composable
fun SighnInPreview() {


    BagTheme {

        Surface(
            color = BackgroundMain, modifier = Modifier.fillMaxSize()
        ) {
            SignInScreen()
        }

    }
}


@Composable
fun SignInScreen() {
    val uiController = rememberSystemUiController()
    SideEffect {
        uiController.setStatusBarColor(Blue)
    }
    val navigation = getNavController()
    val viewmodel = getViewModel<SignInViewModel>()

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Blue)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            IconApp()
            MainCardView(navigation, viewmodel) {
                viewmodel.SignInUser()
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextField(
    hint: String, edtValue: String, icon: Int, onValueChanges: (String) -> Unit
) {
    OutlinedTextField(label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(12.dp)
            .padding(start = 30.dp),
        shape = Shapes.medium,
        leadingIcon = { Icon(painterResource(icon), null) }

    )
}

@Composable
fun IconApp() {
    Surface(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
    ) {
        Image(
            modifier = Modifier.padding(14.dp),
            painter = painterResource(id = R.drawable.ic_icon_app),
            contentDescription = null
        )
    }
}


@Composable
fun MainCardView(navigation: NavController, viewModel: SignInViewModel, SignInEvent: () -> Unit) {
    val email = viewModel.email.observeAsState("")
    val password = viewModel.password.observeAsState("")
    val context = LocalContext.current



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = Shapes.medium,
        colors = CardDefaults.cardColors(Color.White)

    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                modifier = Modifier.padding(top = 18.dp, bottom = 18.dp),
                text = "Sign In",
                style = TextStyle(color = Blue, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            )
        }



        MainTextField(
            hint = "Email",
            edtValue = email.value,
            icon = R.drawable.ic_email
        ) { viewModel.email.value = it }
        MainTextFieldPassword(
            hint = "Password",
            edtValue = password.value,
            icon = R.drawable.ic_password
        ) { viewModel.password.value = it }



        Button(onClick = {

            if (email.value.isNotEmpty() && password.value.isNotEmpty()) {

                if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
                    if(NetworkChecker(context).isInternetConnected){
                        SignInEvent.invoke()
                    }else{
                        Toast.makeText(context, "Please Connect to Internet", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(context, "email format is not true! ", Toast.LENGTH_SHORT).show()
                }


            } else {
                Toast.makeText(context, "please write data first ...", Toast.LENGTH_SHORT).show()
            }


        }, modifier = Modifier.padding(bottom = 8.dp, top = 28.dp, start = 130.dp)) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Log In"

            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 18.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )

        {
            Text(text = "Don't have an account ? ")
            TextButton(
                onClick = {
                    navigation.navigate(MyScreens.SignUpScreen.route) {
                        popUpTo(MyScreens.SignInScreen.route) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = "Register Here ", color = Blue)
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextFieldPassword(
    hint: String,
    edtValue: String,
    icon: Int,
    onValueChanges: (String) -> Unit
) {
    val passwordVisible = remember { mutableStateOf(false) }
    OutlinedTextField(label = { Text(hint) },
        value = edtValue,
        singleLine = true,
        onValueChange = onValueChanges,
        placeholder = { Text(hint) },
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(12.dp)
            .padding(start = 30.dp),
        shape = Shapes.medium,
        leadingIcon = { Icon(painterResource(icon), null) },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image =
                if (passwordVisible.value) painterResource(R.drawable.ic_invisible) else painterResource(
                    id = R.drawable.ic_visible
                )
            Icon(painter = image, contentDescription = null, modifier = Modifier.clickable {
                passwordVisible.value = !passwordVisible.value
            })
        }


    )
}
