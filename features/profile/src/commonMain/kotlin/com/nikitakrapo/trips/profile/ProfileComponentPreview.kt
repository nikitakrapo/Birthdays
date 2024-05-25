package com.nikitakrapo.trips.profile

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ProfileComponentPreview : ProfileComponent {

    override val state: StateFlow<ProfileScreenState> = MutableStateFlow(
        ProfileScreenState(
            username = "nikitakrapo",
            birthday = "15 Feb 2002",
            isLoading = false,
            isError = false,
            showLogoutDialog = false,
        )
    )

    override fun onLogoutClick() {}
    override fun onLogoutConfirmed() {}
    override fun onLogoutCancelled() {}
    override fun onRefreshClicked() {}
}