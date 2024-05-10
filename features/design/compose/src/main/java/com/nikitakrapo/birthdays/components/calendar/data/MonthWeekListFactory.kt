package com.nikitakrapo.birthdays.components.calendar.data

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.toJavaLocalDate

private const val MAX_WEEKS = 6

internal fun createListOfWeeks(
    year: Int,
    month: Month,
): List<List<CalendarMonthState.Day>> {
    val localDate = LocalDate(year, month, 1)
    val monthLength = localDate.toJavaLocalDate().lengthOfMonth()
    val firstDayOfWeek = localDate.dayOfWeek
    val weekOffset = firstDayOfWeek.isoDayNumber - 1
    return List(MAX_WEEKS) { week ->
        if (week == 0) {
            val firstWeekDays = firstDayOfWeek.value..DayOfWeek.values().last().value
            (firstWeekDays).mapNotNull { dayOfWeekValue ->
                val dayOfWeek = DayOfWeek(dayOfWeekValue)
                CalendarMonthState.Day(dayOfWeekValue - weekOffset, dayOfWeek)
            }
        } else {
            DayOfWeek.values().mapNotNull { dayOfWeek ->
                val dayValue = week * 7 + dayOfWeek.value - weekOffset
                if (dayValue <= monthLength) {
                    CalendarMonthState.Day(dayValue, dayOfWeek)
                } else {
                    null
                }
            }
        }
    }
}