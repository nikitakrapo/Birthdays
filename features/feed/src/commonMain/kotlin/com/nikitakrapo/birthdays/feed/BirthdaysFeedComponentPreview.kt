package com.nikitakrapo.birthdays.feed

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

object BirthdaysFeedComponentPreview : BirthdaysFeedComponent {

    override val state: StateFlow<BirthdaysFeedScreenState> = MutableStateFlow(
        BirthdaysFeedScreenState.Loading
    )

    override fun onDateSelected(date: LocalDate) = Unit
}