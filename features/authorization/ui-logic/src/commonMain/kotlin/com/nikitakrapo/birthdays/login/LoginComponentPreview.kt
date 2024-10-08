package com.nikitakrapo.birthdays.login

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object LoginComponentPreview : LoginComponent {

    override val stateValue: Value<LoginScreenState> = MutableValue(
        LoginScreenState(
            "",
            "",
            false,
            null,
        )
    )
    override val state: StateFlow<LoginScreenState> = MutableStateFlow(stateValue.value)

    override fun onEmailTextChanged(text: String) {}
    override fun onPasswordTextChanged(text: String) {}
    override fun onRegisterClicked() {}
    override fun onDoneClicked() {}
}