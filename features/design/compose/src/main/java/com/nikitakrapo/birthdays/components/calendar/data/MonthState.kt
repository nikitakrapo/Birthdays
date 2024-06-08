package com.nikitakrapo.birthdays.components.calendar.data

import androidx.compose.runtime.Stable

/**
 * UI state containing all month's days + the ones for previous and past months
 */
@Stable
data class MonthState(
    val weekList: List<List<DayState>>,
)
