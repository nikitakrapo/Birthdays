package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.data.CalendarState
import com.nikitakrapo.birthdays.components.calendar.data.getMonthFromAbsoluteMonth
import com.nikitakrapo.birthdays.components.calendar.data.getYearFromAbsoluteMonth
import com.nikitakrapo.birthdays.components.calendar.data.numberOfMonths
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarLazyListState
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarState
import com.nikitakrapo.birthdays.components.calendar.data.rememberMonthState
import com.nikitakrapo.trips.design.theme.TripsTheme
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.format.DayOfWeekNames

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarDateChooser(
    modifier: Modifier = Modifier,
    state: CalendarState,
    onDaySelected: (LocalDate) -> Unit,
) {
    LaunchedEffect(state.selectedDate) {
        onDaySelected(state.selectedDate)
    }
    Column(
        modifier = modifier
            .width(CalendarDefaults.MediumWidth),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val monthLazyListState = rememberCalendarLazyListState(state)
        CalendarMonthHeader()
        LazyRow(
            state = monthLazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = monthLazyListState),
        ) {
            items(state.numberOfMonths) { monthIndexAbsolute ->
                val month = getMonthFromAbsoluteMonth(monthIndexAbsolute)
                val year = getYearFromAbsoluteMonth(monthIndexAbsolute, state.yearRange)
                val selectedDay = state.selectedDate.dayOfMonth.takeIf {
                    state.selectedDate.year == year && state.selectedDate.month == month
                }
                val lastDay = state.lastDay
                    ?.takeIf { it.year == year && it.month == month }
                    ?.dayOfMonth
                val monthState = rememberMonthState(
                    year = year,
                    month = month,
                    selectedDay = selectedDay,
                    lastDay = lastDay,
                )
                CalendarMonth(
                    modifier = Modifier
                        .fillParentMaxWidth(),
                    month = monthState,
                    onDayClicked = {
                        val localDate = LocalDate(year, month, it.value)
                        state.selectedDate = localDate
                    },
                )
            }
        }
    }
}

@Composable
private fun CalendarMonthHeader(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        // TODO TRIPSMOBILE-12: add localization
        DayOfWeekNames.ENGLISH_ABBREVIATED.names.forEach {
            Box(
                modifier = Modifier
                    .size(48.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = it.first().toString(),
                    style = TripsTheme.typography.titleMedium,
                    color = TripsTheme.colorScheme.secondary,
                )
            }
        }
    }
}

@Preview
@Composable
private fun CalendarDateChooserPreview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            CalendarDateChooser(
                state = rememberCalendarState(
                    initialMonth = Month.APRIL,
                    initialYear = 2024,
                    initialDay = 28,
                    yearRange = 1900..2025,
                ),
                onDaySelected = {},
            )
        }
    }
}
