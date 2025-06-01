package com.nikitakrapo.birthdays

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

class AddBirthdayComponentPreview(
    state: AddBirthdayState = AddBirthdayState(
        nameText = "Nikita Nekronov",
        date = LocalDate(year = 2000, month = Month.JANUARY, dayOfMonth = 1),
        isAddActive = true,
        yearRange = 1900..2025,
        lastSelectableDatetimeMs = LocalDate(2025,5,25)
            .toEpochDays().toLong() * 86_400_000
    ),
) : AddBirthdayComponent {
    override val state = MutableStateFlow(state)
    override fun onNameTextChanged(text: String) = Unit
    override fun onDateChanged(dateTimeMillis: Long) = Unit
    override fun onAddClicked() = Unit
    override fun onBackClicked() = Unit
}