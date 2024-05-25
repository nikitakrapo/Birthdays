package com.nikitakrapo.trips.profile

data class ProfileScreenState(
    val username: String,
    val birthday: String,
    val isLoading: Boolean,
    val isError: Boolean,
    val showLogoutDialog: Boolean,
)
