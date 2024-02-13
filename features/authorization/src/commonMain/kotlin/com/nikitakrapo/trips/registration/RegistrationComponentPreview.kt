package com.nikitakrapo.trips.registration

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal object RegistrationComponentPreview : RegistrationComponent {
    override val state: StateFlow<RegistrationScreenState> = MutableStateFlow(
        RegistrationScreenState(
            email = "",
            password = "",
            isLoading = false,
            error = null,
        )
    )

    override fun onEmailTextChanged(text: String) {}
    override fun onPasswordTextChanged(text: String) {}
    override fun onRegisterClicked() {}
    override fun onBackClicked() {}
}