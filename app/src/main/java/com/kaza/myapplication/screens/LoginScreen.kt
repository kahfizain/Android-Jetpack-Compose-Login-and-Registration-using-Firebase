package com.kaza.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaza.myapplication.R
import com.kaza.myapplication.componensts.ButtonComponent
import com.kaza.myapplication.componensts.ClickableRegisterTextComponent
import com.kaza.myapplication.componensts.DividerTextComponent
import com.kaza.myapplication.componensts.HeadingTextComponent
import com.kaza.myapplication.componensts.NormalTextComponent
import com.kaza.myapplication.componensts.PasswordTextFieldComponent
import com.kaza.myapplication.componensts.TextFieldComponent
import com.kaza.myapplication.componensts.UnderLineNormalTextComponent
import com.kaza.myapplication.data.LoginViewModel
import com.kaza.myapplication.data.UIEvent
import com.kaza.myapplication.navigation.PostOfficeAppRouter
import com.kaza.myapplication.navigation.Screen

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            NormalTextComponent(value = stringResource(id = R.string.app_name))
            HeadingTextComponent(value = stringResource(id = R.string.welcome_back))
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.ic_email),
                keyboardType = KeyboardType.Email,
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.EmailChanged(it))
                },
                errorStatus = loginViewModel.registrationUIState.value.emailError

            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    loginViewModel.onEvent(UIEvent.PasswordChanged(it))

                },
                errorStatus = loginViewModel.registrationUIState.value.passwordError

            )

            Spacer(modifier = Modifier.height(10.dp))

            UnderLineNormalTextComponent(
                value = stringResource(id = R.string.forget_password),
                onTextSelected = {

                })

            Spacer(modifier = Modifier.height(200.dp))

            ButtonComponent(value = stringResource(id = R.string.login),
                onButtonClicked = {
                    loginViewModel.onEvent(UIEvent.RegisterButtonClicked)
                })

            Spacer(modifier = Modifier.height(10.dp))

            DividerTextComponent()

            ClickableRegisterTextComponent(
                value = stringResource(id = R.string.dont_have_account),
                onTextSelected = {
                    PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
                })


        }


    }
}


@Preview
@Composable
fun DefaultPreviewOfLoginScreen() {
    LoginScreen()
}