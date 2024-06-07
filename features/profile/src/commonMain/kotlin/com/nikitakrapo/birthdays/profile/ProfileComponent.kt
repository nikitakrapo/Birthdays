package com.nikitakrapo.birthdays.profile

import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<ProfileScreenState>

    fun onLogoutClick()
    fun onLogoutConfirmed()
    fun onLogoutCancelled()
    fun onRefreshClicked()
}