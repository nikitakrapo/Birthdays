package com.nikitakrapo.birthdays.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.Calendar
import com.nikitakrapo.birthdays.components.calendar.data.CalendarRange
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarState
import com.nikitakrapo.birthdays.components.calendar.info.DateInfo
import com.nikitakrapo.birthdays.components.calendar.info.DateInfoProvider
import com.nikitakrapo.birthdays.components.shimmer.ShimmerBox
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.nikitakrapo.birthdays.utils.Crossfade
import kotlinx.datetime.LocalDate

@Composable
fun BirthdaysFeedScreen(
    modifier: Modifier = Modifier,
    component: BirthdaysFeedComponent,
) {
    val state by component.state.collectAsState()

    Crossfade(
        modifier = modifier,
        targetState = state,
        contentKey = { it::class },
    ) { screenState ->
        when (screenState) {
            BirthdaysFeedScreenState.Loading -> LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    BirthdaysCalendarShimmer()
                }
            }
            is BirthdaysFeedScreenState.Loaded -> LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    BirthdaysCalendar(
                        state = screenState,
                        component = component,
                    )
                }
            }
        }
    }
}

@Composable
private fun BirthdaysCalendar(
    modifier: Modifier = Modifier,
    state: BirthdaysFeedScreenState.Loaded,
    component: BirthdaysFeedComponent,
) {
    Box(
        modifier = modifier
            .clip(BirthdaysTheme.shapes.medium)
            .background(BirthdaysTheme.colorScheme.primaryContainer)
            .padding(16.dp),
    ) {
        val calendarState = rememberCalendarState(
            initialDate = state.initialDate,
            initialSelectedDate = null,
            calendarRange = CalendarRange(state.startDate, state.endDate)
        )
        Calendar(
            modifier = Modifier
                .align(Alignment.Center),
            state = calendarState,
            yearPickerEnabled = true,
            dateInfoProvider = state.birthdays.dateInfoProvider(),
            onDaySelected = component::onDateSelected,
        )
    }
}

private fun List<LocalDate>.dateInfoProvider(): DateInfoProvider = DateInfoProvider { date ->
    DateInfo(
        hasEvents = any { it == date },
    )
}

@Composable
private fun BirthdaysCalendarShimmer(
    modifier: Modifier = Modifier,
) {
    ShimmerBox(
        modifier = modifier
            .size(
                width = 336.dp + 32.dp,
                height = 384.dp + 32.dp,
            )
            .clip(BirthdaysTheme.shapes.medium)
            .background(BirthdaysTheme.colorScheme.primaryContainer),
    )
}
