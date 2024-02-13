package com.nikitakrapo.trips.login

data class LoginScreenState(
    val email: String,
    val password: String,
    val isLoading: Boolean,
    val error: String?,
)
