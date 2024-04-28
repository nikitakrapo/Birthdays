package com.nikitakrapo.birthdays.components.calendar.data

import androidx.compose.runtime.Stable
import kotlinx.datetime.DayOfWeek

/**
 * UI state containing all month's days + the ones for previous and past months
 */
@Stable
data class CalendarMonthState(
    val weekList: List<List<Day>>,
) {
    data class Day(
        val value: Int,
        val dayOfWeek: DayOfWeek,
    )
}