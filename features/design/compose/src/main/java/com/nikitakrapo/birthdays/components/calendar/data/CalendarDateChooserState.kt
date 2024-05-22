package com.nikitakrapo.birthdays.components.calendar.data

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

@Stable
class CalendarDateChooserState internal constructor(
    initialDate: LocalDate,
    initialSelectedDate: LocalDate?,
    val calendarRange: CalendarRange,
) {

    internal var selectedDate: LocalDate? by mutableStateOf(initialSelectedDate)

    internal var selectedMonth by mutableStateOf(initialDate.month)

    internal var selectedYear by mutableIntStateOf(initialDate.year)

    internal val initialMonthAbsolute =
        12 * (selectedYear - calendarRange.startDate.year) + initialDate.month.value - 1
}

val CalendarDateChooserState.numberOfMonths: Int get() {
    val numberOfYears = calendarRange.endDate.year - calendarRange.startDate.year + 1
    val startMonthsTrim = calendarRange.startMonthsOffset
    val endMonthsTrim = 12 - calendarRange.endDate.month.value
    return numberOfYears * 12 - startMonthsTrim - endMonthsTrim
}

fun CalendarDateChooserState.getMonthFromAbsoluteMonth(absoluteMonth: Int): Month {
    val startMonthsOffset = calendarRange.startMonthsOffset
    return Month((absoluteMonth + startMonthsOffset) % 12 + 1)
}

fun CalendarDateChooserState.getYearFromAbsoluteMonth(absoluteMonth: Int): Int {
    val startMonthsOffset = calendarRange.startMonthsOffset
    return (absoluteMonth + startMonthsOffset) / 12 + calendarRange.startDate.year
}

fun CalendarDateChooserState.getAbsoluteMonthForYear(year: Int): Int {
    check(year >= calendarRange.startDate.year && year <= calendarRange.endDate.year) {
        "Wrong year"
    }
    val absoluteYearMonth = (year - calendarRange.startDate.year) * 12
    val startMonthsOffset = calendarRange.startMonthsOffset
    return (absoluteYearMonth - startMonthsOffset + selectedMonth.ordinal)
        .coerceAtMost(numberOfMonths - 1)
}

private val CalendarRange.startMonthsOffset get() = startDate.month.ordinal

@Composable
fun rememberMonthState(
    year: Int,
    month: Month,
    selectedDay: Int?,
    dayRange: IntRange,
): CalendarMonthState {
    val weekList = remember(year, month) {
        createListOfWeeks(year, month)
    }
    return remember(selectedDay, dayRange) {
        CalendarMonthState(
            weekList = weekList,
            selectedDay = selectedDay,
            dayRange = dayRange,
        )
    }
}

@Composable
fun rememberCalendarLazyListState(
    state: CalendarDateChooserState,
) : LazyListState {
    val lazyListState = rememberLazyListState(
        initialFirstVisibleItemIndex = state.initialMonthAbsolute,
    )
    val firstVisibleItemIndex by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex }
    }
    LaunchedEffect(key1 = firstVisibleItemIndex) {
        val month = state.getMonthFromAbsoluteMonth(firstVisibleItemIndex)
        val year = state.getYearFromAbsoluteMonth(firstVisibleItemIndex)
        state.selectedMonth = month
        state.selectedYear = year
    }
    return lazyListState
}

@Composable
fun rememberCalendarState(
    initialDate: LocalDate,
    initialSelectedDate: LocalDate?,
    calendarRange: CalendarRange,
) : CalendarDateChooserState {
    return remember(calendarRange) {
        CalendarDateChooserState(
            initialDate = initialDate,
            initialSelectedDate = initialSelectedDate,
            calendarRange = calendarRange,
        )
    }
}
