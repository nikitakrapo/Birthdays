package com.nikitakrapo.birthdays.profile

data class ProfileScreenState(
    val username: String,
    val birthday: String,
    val isLoading: Boolean,
    val isError: Boolean,
    val showLogoutDialog: Boolean,
)
