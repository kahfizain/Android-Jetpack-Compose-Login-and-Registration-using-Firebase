package com.kaza.myapplication.fiture.register.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kaza.myapplication.data.rules.Validator
import com.kaza.myapplication.fiture.register.data.RegisterUIState

class RegisterViewModel : ViewModel() {
    private val TAG = RegisterViewModel::class.simpleName

    var registerUIState = mutableStateOf(RegisterUIState())
    var allValidationPassed = mutableStateOf(false)

    fun onEvent(event: RegisterUIEvent) {
        validateDateWithRules()
        when (event) {
            is RegisterUIEvent.FirstNameChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is RegisterUIEvent.LastNameChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is RegisterUIEvent.EmailChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    email = event.email
                )
            }

            is RegisterUIEvent.PasswordChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    password = event.password
                )
            }

            is RegisterUIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }

    }

    private fun signUp() {
        Log.d(TAG, registerUIState.value.toString())

        createUserFirebase(
            email = registerUIState.value.email,
            password = registerUIState.value.password
        )

    }

    private fun validateDateWithRules() {
        val fNameResult = Validator.validateFirsName(
            fName = registerUIState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lName = registerUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = registerUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = registerUIState.value.password
        )

        registerUIState.value = registerUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status
    }

    fun createUserFirebase(email: String, password: String) {

    }
}