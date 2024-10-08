package com.nikitakrapo.birthdays.login

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object LoginComponentPreview : LoginComponent {

    override val state: StateFlow<LoginScreenState> = MutableStateFlow(
        LoginScreenState(
            "",
            "",
            false,
            null,
        )
    )

    override fun onEmailTextChanged(text: String) {}
    override fun onPasswordTextChanged(text: String) {}
    override fun onRegisterClicked() {}
    override fun onDoneClicked() {}
}