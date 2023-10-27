package com.kaza.myapplication.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaza.myapplication.R
import com.kaza.myapplication.ui.theme.componensts.ButtonComponent
import com.kaza.myapplication.ui.theme.componensts.ClickableRegisterTextComponent
import com.kaza.myapplication.ui.theme.componensts.DividerTextComponent
import com.kaza.myapplication.ui.theme.componensts.HeadingTextComponent
import com.kaza.myapplication.ui.theme.componensts.NormalTextComponent
import com.kaza.myapplication.ui.theme.componensts.PasswordTextFieldComponent
import com.kaza.myapplication.ui.theme.componensts.TextFieldComponent
import com.kaza.myapplication.ui.theme.componensts.UnderLineNormalTextComponent
import com.kaza.myapplication.feature.login.data.LoginUIEvent
import com.kaza.myapplication.feature.login.data.LoginViewModel
import com.kaza.myapplication.utils.navigation.PostOfficeAppRouter
import com.kaza.myapplication.utils.navigation.Screen

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError,
                    errorMsg = loginViewModel.loginUIState.value.emailMsgError

                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))

                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError,
                    errorMsg = loginViewModel.loginUIState.value.passwordMsgError

                )

                Spacer(modifier = Modifier.height(10.dp))

                UnderLineNormalTextComponent(
                    value = stringResource(id = R.string.forget_password),
                    onTextSelected = {
                    })

                Spacer(modifier = Modifier.height(200.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LogInButtonClicked)
                        loginViewModel.errorMsgServer(context)
                    }, true
                )

                Spacer(modifier = Modifier.height(10.dp))

                DividerTextComponent()

                ClickableRegisterTextComponent(
                    value = stringResource(id = R.string.dont_have_account),
                    onTextSelected = {
                        PostOfficeAppRouter.navigateTo(Screen.SignUpScreen)
                    })
            }
        }

        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }

}


@Preview
@Composable
fun DefaultPreviewOfLoginScreen() {
    LoginScreen()
}