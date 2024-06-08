package com.nikitakrapo.birthdays.profile

import com.arkivanov.decompose.router.slot.ChildSlot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

object ProfileEditComponentPreview : ProfileEditComponent {

    override val dialogSlot = MutableStateFlow(
        ChildSlot<Any, ProfileEditComponent.ProfileEditDialog>(
            null,
        )
    )

    override val state: StateFlow<ProfileEditScreenState> = MutableStateFlow(
        ProfileEditScreenState(
            username = "nikitakrapo",
            birthday = LocalDate(2000, 1, 1),
            isLoading = false,
            isConfirmAllowed = false,
        )
    )

    override fun onUsernameChanged(username: String) = Unit
    override fun onChangeBirthdayClicked() = Unit
    override fun onConfirmClicked() = Unit
    override fun onBackClicked() = Unit
}