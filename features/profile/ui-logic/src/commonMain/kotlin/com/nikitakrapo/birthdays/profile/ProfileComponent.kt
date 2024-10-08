package com.nikitakrapo.birthdays.profile

import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<ProfileScreenState>
    val showLogoutDialog: StateFlow<Boolean>

    fun onEditProfileClicked()
    fun onLogoutClicked()
    fun onLogoutConfirmed()
    fun onLogoutCancelled()
    fun onRetryClicked()
}