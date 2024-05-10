package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.data.CalendarMonthState
import com.nikitakrapo.trips.design.theme.TripsTheme
import kotlinx.datetime.DayOfWeek

@Composable
fun CalendarMonth(
    modifier: Modifier = Modifier,
    month: CalendarMonthState,
    onDayClicked: (day: CalendarMonthState.Day) -> Unit,
) {
    Column(
        modifier = modifier
            .width(CalendarDefaults.MediumWidth),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        month.weekList.forEachIndexed { rowIndex, weekDays ->
            Row {
                DayOfWeek.values().forEach { dayOfWeek ->
                    val day = weekDays.find { it.dayOfWeek == dayOfWeek }
                    if (day != null) {
                        CalendarDay(
                            day = day.value,
                            isChosen = day.value == month.selectedDay,
                            isActive = day.value in month.dayRange,
                            onClick = { onDayClicked(day) },
                        )
                    } else {
                        Spacer(modifier = Modifier.size(48.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CalendarMonthPreview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            val delta = 3
            CalendarMonth(
                month = CalendarMonthState(
                    weekList = (0..5).mapNotNull { week ->
                        if (week == 0) {
                            DayOfWeek.values().takeLast(7 - delta).mapIndexed { index, dayOfWeek ->
                                CalendarMonthState.Day(index + 1, dayOfWeek = dayOfWeek)
                            }
                        } else {
                            DayOfWeek.values().mapIndexedNotNull { index, dayOfWeek ->
                                val dayValue = index - delta + 1 + 7 * week
                                if (dayValue <= 31) {
                                    CalendarMonthState.Day(dayValue, dayOfWeek = dayOfWeek)
                                } else {
                                    null
                                }
                            }
                        }.takeIf { it.isNotEmpty() }
                    },
                    selectedDay = 5,
                    dayRange = 1..25,
                ),
                onDayClicked = {},
            )
        }
    }
}
