package com.nikitakrapo.birthdays.components.calendar.data

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.toJavaLocalDate
import java.time.Year
import kotlin.math.ceil

@Stable
class CalendarState internal constructor(
    initialMonth: Month,
    initialYear: Year,
    val yearRange: IntRange,
) {

    internal var selectedMonth by mutableStateOf(initialMonth)

    internal var selectedYear by mutableStateOf(initialYear)

    internal val initialMonthAbsolute =
        12 * (selectedYear.value - yearRange.first) + initialMonth.value - 1
}

val CalendarState.numberOfYears get() = yearRange.last - yearRange.first + 1
val CalendarState.numberOfMonths get() = numberOfYears * 12

@Composable
fun rememberMonthState(
    year: Year,
    month: Month,
): CalendarMonthState {
    return remember {
        val localDate = LocalDate(year.value, month, 1)
        val monthLength = localDate.toJavaLocalDate().lengthOfMonth()
        val firstDayOfWeek = localDate.dayOfWeek
        val weekOffset = firstDayOfWeek.isoDayNumber - 1
        CalendarMonthState(
            weekList = List(6) { week ->
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
        )
    }
}

internal fun getMonthFromAbsoluteMonth(absoluteMonth: Int): Month {
    return Month(absoluteMonth % 12 + 1)
}

internal fun getYearFromAbsoluteMonth(absoluteMonth: Int, yearRange: IntRange): Year {
    return Year.of(absoluteMonth / 12 + yearRange.first)
}

@Composable
fun rememberCalendarLazyListState(
    state: CalendarState,
) : LazyListState {
    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = state.initialMonthAbsolute,
    )
    val firstVisibleItemIndex by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex }
    }
    LaunchedEffect(key1 = firstVisibleItemIndex) {
        val month = getMonthFromAbsoluteMonth(firstVisibleItemIndex)
        val year = getYearFromAbsoluteMonth(firstVisibleItemIndex, state.yearRange)
        state.selectedMonth = month
        state.selectedYear = year
    }
    return lazyListState
}

@Composable
fun rememberCalendarState(
    initialMonth: Month,
    initialYear: Year,
    yearRange: IntRange,
) : CalendarState {
    return remember {
        CalendarState(
            initialMonth = initialMonth,
            initialYear = initialYear,
            yearRange = yearRange,
        )
    }
}
