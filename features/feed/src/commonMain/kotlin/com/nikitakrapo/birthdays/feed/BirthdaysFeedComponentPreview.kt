package com.nikitakrapo.birthdays.feed

import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.LocalDate

object BirthdaysFeedComponentPreview : BirthdaysFeedComponent {

    override val state: StateFlow<BirthdaysFeedScreenState> = MutableStateFlow(
        BirthdaysFeedScreenState.Loading
    )

    override fun onDateSelected(date: LocalDate) = Unit
    override fun onBirthdayClicked(birthday: Birthday) = Unit
}