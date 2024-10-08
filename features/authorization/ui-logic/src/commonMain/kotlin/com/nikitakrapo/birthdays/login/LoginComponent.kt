package com.nikitakrapo.birthdays.login

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {

    val stateValue: Value<LoginScreenState>
    val state: StateFlow<LoginScreenState>

    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onRegisterClicked()
    fun onDoneClicked()
}