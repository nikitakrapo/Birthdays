package com.nikitakrapo.trips.profile

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ProfileComponentPreview : ProfileComponent {

    override val state: StateFlow<ProfileScreenState> = MutableStateFlow(
        ProfileScreenState(
            email = "sample@email.net",
            showLogoutDialog = false,
        )
    )

    override fun onSettingsClick() {}
    override fun onLogoutClick() {}
    override fun onLogoutConfirmed() {}
    override fun onLogoutCancelled() {}
}