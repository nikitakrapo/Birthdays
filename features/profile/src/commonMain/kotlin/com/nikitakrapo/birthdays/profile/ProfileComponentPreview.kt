package com.nikitakrapo.birthdays.profile

import com.nikitakrapo.birthdays.model.ProfileInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

object ProfileComponentPreview : ProfileComponent {

    override val state: StateFlow<ProfileScreenState> = MutableStateFlow(
        ProfileScreenState.Loaded(
            profileInfo = ProfileInfo(
                username = "nikitakrapo",
                birthday = LocalDate(2000, 1, 1),
            ),
        )
    )
    override val showLogoutDialog: StateFlow<Boolean> = MutableStateFlow(false)

    override fun onEditProfileClicked() {}
    override fun onLogoutClicked() {}
    override fun onLogoutConfirmed() {}
    override fun onLogoutCancelled() {}
    override fun onRetryClicked() {}
}