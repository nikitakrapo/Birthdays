package com.nikitakrapo.birthdays.wizard

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
internal class DatetimeSelectableDates(
    private val selectableDateTimes: Pair<LocalDateTime, LocalDateTime>,
) : SelectableDates {

    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val date = Instant.fromEpochMilliseconds(utcTimeMillis).toLocalDateTime(TimeZone.UTC)
        return selectableDateTimes.first <= date && date <= selectableDateTimes.second
    }

    override fun isSelectableYear(year: Int): Boolean {
        return year in selectableDateTimes.first.year..selectableDateTimes.second.year
    }
}