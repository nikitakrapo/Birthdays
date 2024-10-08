package com.nikitakrapo.birthdays.feed

import com.nikitakrapo.birthdays.model.Birthday
import kotlinx.datetime.LocalDate

sealed interface BirthdaysFeedScreenState {

    data object Loading : BirthdaysFeedScreenState

    data class Loaded(
        val initialDate: LocalDate,
        val startDate: LocalDate,
        val endDate: LocalDate,
        val birthdays: List<Birthday>,
    ) : BirthdaysFeedScreenState
}