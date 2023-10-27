package com.kaza.myapplication.feature.home.data

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.kaza.myapplication.utils.navigation.PostOfficeAppRouter
import com.kaza.myapplication.utils.navigation.Screen


class HomeViewModel : ViewModel() {

    var errorMsg : String = ""
    private val _batteryPercentage = MutableLiveData<Int>()
    val batteryPercentage: LiveData<Int> = _batteryPercentage
    fun logOut() {
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                PostOfficeAppRouter.navigateTo(Screen.LoginScreen)
            } else {
                errorMsg = "Inside sign out is not complete"
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

    fun updateBatteryPercentage(context: Context) {
        val batteryIntent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        val level = batteryIntent?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) // Battery level in percentage
        _batteryPercentage.value = level
    }
}