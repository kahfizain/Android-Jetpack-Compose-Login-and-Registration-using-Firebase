package com.kaza.myapplication.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kaza.myapplication.navigation.PostOfficeAppRouter
import com.kaza.myapplication.navigation.Screen
import com.kaza.myapplication.fiture.login.LoginScreen
import com.kaza.myapplication.fiture.register.SignUpScreen
import com.kaza.myapplication.fiture.termsconditions.TermsAndConditionsScreen

@Composable
fun PostOfficeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Crossfade(targetState = PostOfficeAppRouter.currentScreen, label = "") { currentState->
            when(currentState.value){
                is Screen.LoginScreen->{
                    LoginScreen()
                }
                is Screen.SignUpScreen->{
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen ->{
                    TermsAndConditionsScreen()
                }
            }
        }

    }

}