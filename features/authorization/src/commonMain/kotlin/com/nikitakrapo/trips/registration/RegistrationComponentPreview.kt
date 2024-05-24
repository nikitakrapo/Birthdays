package com.nikitakrapo.trips.registration

import com.arkivanov.decompose.router.slot.ChildSlot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal object RegistrationComponentPreview : RegistrationComponent {

    override val dialogSlot: StateFlow<ChildSlot<*, RegistrationComponent.RegistrationDialog>> = MutableStateFlow(
        ChildSlot<Any, RegistrationComponent.RegistrationDialog>(
            null,
        )
    )

    override val state: StateFlow<RegistrationScreenState> = MutableStateFlow(
        RegistrationScreenState(
            username = "",
            email = "",
            password = "",
            birthday = null,
            isLoading = false,
            error = null,
        )
    )

    override fun onUsernameTextChanged(text: String) {}
    override fun onEmailTextChanged(text: String) {}
    override fun onPasswordTextChanged(text: String) {}
    override fun onSelectBirthdayClicked() {}
    override fun onRegisterClicked() {}
    override fun onBackClicked() {}
}