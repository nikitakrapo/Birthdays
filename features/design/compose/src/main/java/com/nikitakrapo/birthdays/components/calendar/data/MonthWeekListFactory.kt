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
    selectedDay: Int?,
    dayRange: IntRange,
): List<List<DayState>> {
    val localDate = LocalDate(year, month, 1)
    val monthLength = localDate.toJavaLocalDate().lengthOfMonth()
    val firstDayOfWeek = localDate.dayOfWeek
    val weekOffset = firstDayOfWeek.isoDayNumber - 1
    return List(MAX_WEEKS) { week ->
        if (week == 0) {
            val firstWeekDays = firstDayOfWeek.value..DayOfWeek.values().last().value
            firstWeekDays.mapNotNull { dayOfWeekValue ->
                val dayValue = dayOfWeekValue - weekOffset
                DayState(
                    day = LocalDate(year, month, dayValue),
                    isChosen = dayValue == selectedDay,
                    isActive = dayValue in dayRange,
                )
            }
        } else {
            DayOfWeek.values().mapNotNull { dayOfWeek ->
                val dayValue = week * 7 + dayOfWeek.value - weekOffset
                if (dayValue <= monthLength) {
                    DayState(
                        day = LocalDate(year, month, dayValue),
                        isChosen = dayValue == selectedDay,
                        isActive = dayValue in dayRange,
                    )
                } else {
                    null
                }
            }
        }
    }
}