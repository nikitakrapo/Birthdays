package com.nikitakrapo.birthdays.feed

import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

interface BirthdaysFeedComponent {

    val state: StateFlow<BirthdaysFeedScreenState>

    fun onDateSelected(date: LocalDate)
}