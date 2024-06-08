package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.data.DayState
import com.nikitakrapo.birthdays.components.calendar.data.MonthState
import com.nikitakrapo.birthdays.components.calendar.data.getMonthState
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import kotlinx.datetime.DayOfWeek
import java.time.Month

@Composable
fun CalendarMonth(
    modifier: Modifier = Modifier,
    month: MonthState,
    onDayClicked: (DayState) -> Unit,
    dayContent: @Composable RowScope.(DayState) -> Unit = {
        Day(
            state = it,
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
            CalendarMonth(
                month = getMonthState(
                    year = 2024,
                    month = Month.MAY,
                    selectedDay = 5,
                    dayRange = 1..25,
                ),
                onDayClicked = {},
            )
        }
    }
}
