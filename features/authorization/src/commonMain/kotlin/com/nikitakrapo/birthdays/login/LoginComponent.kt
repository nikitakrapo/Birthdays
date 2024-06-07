package com.nikitakrapo.birthdays.login

import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {

    val state: StateFlow<LoginScreenState>

    fun onEmailTextChanged(text: String)
    fun onPasswordTextChanged(text: String)
    fun onRegisterClicked()
    fun onDoneClicked()
}