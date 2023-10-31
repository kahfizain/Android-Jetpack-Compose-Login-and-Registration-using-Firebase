package com.kaza.myapplication.feature.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaza.myapplication.R
import com.kaza.myapplication.feature.home.data.HomeViewModel
import com.kaza.myapplication.ui.theme.componensts.ButtonComponent
import com.kaza.myapplication.ui.theme.componensts.HeadingTextComponent


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

    val context = LocalContext.current
    homeViewModel.getUserData()
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
            HeadingTextComponent(value = "Battery Level : ${homeViewModel.getBatteryManager(context)} %")
            HeadingTextComponent(value = "Email: ${homeViewModel.emailId.value}")

            ButtonComponent(value = stringResource(id = R.string.logout),
                onButtonClicked = {
                    homeViewModel.logOut()
                    if(homeViewModel.errorMsg!=""){
                        Toast.makeText( context, homeViewModel.errorMsg, Toast.LENGTH_SHORT).show()
                    }
            },isEnabled = true)
        }

    }

}


@Preview
@Composable
fun DefaultPreviewOfHomeScreen() {
    HomeScreen()
}