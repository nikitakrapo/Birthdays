package com.nikitakrapo.trips.profile

import kotlinx.coroutines.flow.StateFlow

interface ProfileComponent {

    val state: StateFlow<ProfileScreenState>

    fun onSettingsClick()
    fun onLogoutClick()
    fun onLogoutConfirmed()
    fun onLogoutCancelled()
}