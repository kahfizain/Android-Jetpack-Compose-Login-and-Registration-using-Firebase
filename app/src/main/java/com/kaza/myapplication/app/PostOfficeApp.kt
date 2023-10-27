package com.kaza.myapplication.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaza.myapplication.feature.home.HomeScreen
import com.kaza.myapplication.feature.home.data.HomeViewModel
import com.kaza.myapplication.feature.login.LoginScreen
import com.kaza.myapplication.feature.signup.SignUpScreen
import com.kaza.myapplication.feature.termsconditions.TermsAndConditionsScreen
import com.kaza.myapplication.utils.navigation.PostOfficeAppRouter
import com.kaza.myapplication.utils.navigation.Screen

@Composable
fun PostOfficeApp(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            PostOfficeAppRouter.navigateTo(Screen.HomeScreen)
        }

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
                is Screen.HomeScreen->{
                    HomeScreen()
                }
            }
        }

    }

}