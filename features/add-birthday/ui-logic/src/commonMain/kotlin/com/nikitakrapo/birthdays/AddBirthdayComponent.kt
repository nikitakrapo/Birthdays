package com.nikitakrapo.birthdays

import kotlinx.coroutines.flow.StateFlow

interface AddBirthdayComponent {

    val state: StateFlow<AddBirthdayState>

    fun onNameTextChanged(text: String)

    fun onDateChanged(dateTimeMillis: Long)

    fun onAddClicked()

    fun onBackClicked()
}