package com.nikitakrapo.trips.registration

import kotlinx.coroutines.flow.StateFlow

interface RegistrationComponent {

    val state: StateFlow<RegistrationScreenState>

    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onRegisterClicked()
    fun onBackClicked()
}