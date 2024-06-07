package com.nikitakrapo.birthdays.feed.item

import com.nikitakrapo.birthdays.model.Trip
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

fun Trip.toItemState(): TripItemState {
    val currentTimeZone = TimeZone.currentSystemDefault()

    val startLocalDate = startDatetime
        .toLocalDateTime(currentTimeZone)
        .date
    val endLocalDate = endDatetime
        .toLocalDateTime(currentTimeZone)
        .date

    val isYearDifferent = startLocalDate.year - endLocalDate.year != 0
    val isCurrentYear = startLocalDate.year == Clock.System.now().toLocalDateTime(currentTimeZone).year
    val shouldWriteYear = isYearDifferent || !isCurrentYear

    // TODO TRIPSMOBILE-12: add localization
    val dateFormat = LocalDate.Format {
        dayOfMonth(Padding.NONE)
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        if (shouldWriteYear) {
            char(' ')
            year()
        }
    }

    return TripItemState(
        title = title,
        date = "${startLocalDate.format(dateFormat)} - ${endLocalDate.format(dateFormat)}",
    )
}