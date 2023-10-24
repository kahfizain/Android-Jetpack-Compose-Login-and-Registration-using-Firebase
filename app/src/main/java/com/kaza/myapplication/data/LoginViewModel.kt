package com.kaza.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kaza.myapplication.data.rules.Validator

class LoginViewModel : ViewModel() {

    var registrationUIState = mutableStateOf(RegistrationUIState())

    private val TAG = LoginViewModel::class.simpleName

    fun onEvent(event: UIEvent) {

        when (event) {
            is UIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is UIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }

            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }

            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
        validateDateWithRules()
    }

    private fun signUp() {
        Log.d(TAG, registrationUIState.value.toString())


    }

    private fun validateDateWithRules() {
        val fNameResult = Validator.validateFirsName(
            fName = registrationUIState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email =  registrationUIState.value.email
        )
        val  passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError =  lNameResult.status,
            emailError =  emailResult.status,
            passwordError =  passwordResult.status
        )
    }
}