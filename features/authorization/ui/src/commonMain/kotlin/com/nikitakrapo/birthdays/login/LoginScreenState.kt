package com.nikitakrapo.birthdays.login

data class LoginScreenState(
    val email: String,
    val password: String,
    val isLoading: Boolean,
    val error: String?,
)
