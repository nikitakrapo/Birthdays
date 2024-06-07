package com.nikitakrapo.birthdays.profile

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object ProfileComponentPreview : ProfileComponent {

    override val state: StateFlow<ProfileScreenState> = MutableStateFlow(
        ProfileScreenState.Loaded(
            username = "nikitakrapo",
            birthday = "15 Feb 2002",
        )
    )
    override val showLogoutDialog: StateFlow<Boolean> = MutableStateFlow(false)

    override fun onLogoutClick() {}
    override fun onLogoutConfirmed() {}
    override fun onLogoutCancelled() {}
    override fun onRefreshClicked() {}
}