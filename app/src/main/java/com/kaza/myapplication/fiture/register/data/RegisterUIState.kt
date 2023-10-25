package com.kaza.myapplication.fiture.register.data

data class RegisterUIState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",


    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false

)