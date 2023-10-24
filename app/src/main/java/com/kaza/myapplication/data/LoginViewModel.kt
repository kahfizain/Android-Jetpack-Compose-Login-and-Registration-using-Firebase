package com.kaza.myapplication.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var registrationUIState = mutableStateOf(RegistrationUIState())


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
        }
    }
}