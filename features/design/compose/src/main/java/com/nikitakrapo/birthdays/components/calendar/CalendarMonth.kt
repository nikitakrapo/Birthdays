package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.data.DayState
import com.nikitakrapo.birthdays.components.calendar.data.MonthState
import com.nikitakrapo.birthdays.components.calendar.data.getMonthState
import com.nikitakrapo.birthdays.components.calendar.info.DateInfo
import com.nikitakrapo.birthdays.components.calendar.info.DateInfoProvider
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import java.time.Month

@Composable
fun CalendarMonth(
    modifier: Modifier = Modifier,
    month: MonthState,
    dateInfoProvider: DateInfoProvider,
    onDayClicked: (DayState) -> Unit,
    dayContent: @Composable RowScope.(DayState) -> Unit = {
        val hasEvents = dateInfoProvider.getDateInfo(it.day).hasEvents
        Day(
            day = it.day.dayOfMonth,
            isChosen = it.isChosen,
            isActive = it.isActive,
            hasEvents = hasEvents,
            onClick = { onDayClicked(it) },
        )
    },
) {
    Column(
        modifier = modifier
            .width(DateChooserDefaults.MediumWidth),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        month.weekList.forEachIndexed { rowIndex, weekDays ->
            Row {
                DayOfWeek.values().forEach { dayOfWeek ->
                    val day = weekDays.find { it.day.dayOfWeek == dayOfWeek }
                    if (day != null) {
                        dayContent(day)
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
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            var selectedDay by remember { mutableStateOf(5) }
            CalendarMonth(
                month = getMonthState(
                    year = 2024,
                    month = Month.MAY,
                    selectedDay = selectedDay,
                    dayRange = 1..28,
                ),
                dateInfoProvider = { day ->
                    DateInfo(
                        hasEvents = day == LocalDate(2024, Month.MAY, 27),
                    )
                },
                onDayClicked = { selectedDay = it.day.dayOfMonth },
            )
        }
    }
}
