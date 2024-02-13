package com.nikitakrapo.trips.registration

data class RegistrationScreenState(
    val email: String,
    val password: String,
    val isLoading: Boolean,
    val error: String?,
)
