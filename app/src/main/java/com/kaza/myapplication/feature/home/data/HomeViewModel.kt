package com.kaza.myapplication.feature.home.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.kaza.myapplication.utils.navigation.PostOfficeAppRouter
import com.kaza.myapplication.utils.navigation.Screen


class HomeViewModel() : ViewModel() {
    private val TAG = HomeViewModel::class.simpleName

    var errorMsg: String = ""
    val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()
    val emailId: MutableLiveData<String> = MutableLiveData()

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

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }    }

    fun getUserData(){
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }

    /*fun getBatteryLevelLiveData(context: Context): LiveData<Int> {
        return BatteryStatusLiveData(context)

    }*/
}