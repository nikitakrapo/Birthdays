package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.data.CalendarDateChooserState
import com.nikitakrapo.birthdays.components.calendar.data.CalendarRange
import com.nikitakrapo.birthdays.components.calendar.data.getMonthFromAbsoluteMonth
import com.nikitakrapo.birthdays.components.calendar.data.getYearFromAbsoluteMonth
import com.nikitakrapo.birthdays.components.calendar.data.numberOfMonths
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarLazyListState
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarState
import com.nikitakrapo.birthdays.components.calendar.data.rememberMonthState
import com.nikitakrapo.trips.design.theme.TripsTheme
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarDateChooser(
    modifier: Modifier = Modifier,
    state: CalendarDateChooserState,
    onDaySelected: (LocalDate) -> Unit,
) {
    LaunchedEffect(state.selectedDate) {
        state.selectedDate?.let { onDaySelected(it) }
    }
    Column(
        modifier = modifier
            .width(CalendarDefaults.MediumWidth),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val scope = rememberCoroutineScope()
        val monthLazyListState = rememberCalendarLazyListState(state)
        CalendarMonthHeader(
            state = state,
            onNextMonthClicked = {
                scope.launch {
                    monthLazyListState.animateScrollToItem(monthLazyListState.firstVisibleItemIndex + 1)
                }
            },
            onPreviousMonthClicked = {
                scope.launch {
                    monthLazyListState.animateScrollToItem(monthLazyListState.firstVisibleItemIndex - 1)
                }
            },
            nextMonthAvailable = monthLazyListState.canScrollForward,
            previousMonthAvailable = monthLazyListState.canScrollBackward,
        )
        LazyRow(
            state = monthLazyListState,
            flingBehavior = rememberDateChooserSnapFlingBehavior(lazyListState = monthLazyListState),
        ) {
            items(state.numberOfMonths) { monthIndexAbsolute ->
                val currentMonth = state.getMonthFromAbsoluteMonth(monthIndexAbsolute)
                val currentYear = state.getYearFromAbsoluteMonth(monthIndexAbsolute)
                val selectedDay = state.selectedDate?.let { selectedDate ->
                    selectedDate.dayOfMonth.takeIf {
                        selectedDate.year == currentYear && selectedDate.month == currentMonth
                    }
                }
                val firstDay = state.calendarRange.startDate
                    .takeIf { it.year == currentYear && it.month == currentMonth }
                    ?.dayOfMonth
                val lastDay = state.calendarRange.endDate
                    .takeIf { it.year == currentYear && it.month == currentMonth }
                    ?.dayOfMonth
                val monthState = rememberMonthState(
                    year = currentYear,
                    month = currentMonth,
                    selectedDay = selectedDay,
                    dayRange = (firstDay ?: 0)..(lastDay ?: 31),
                )
                CalendarMonth(
                    modifier = Modifier
                        .fillParentMaxWidth(),
                    month = monthState,
                    onDayClicked = {
                        val localDate = LocalDate(currentYear, currentMonth, it.value)
                        state.selectedDate = localDate
                    },
                )
            }
        }
    }
}

@Composable
private fun CalendarMonthHeader(
    state: CalendarDateChooserState,
    onNextMonthClicked: () -> Unit,
    onPreviousMonthClicked: () -> Unit,
    nextMonthAvailable: Boolean,
    previousMonthAvailable: Boolean,
) {
    Spacer(modifier = Modifier.height(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(1.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onPreviousMonthClicked, enabled = previousMonthAvailable) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = stringResource(strings.R.string.cd_date_picker_switch_to_next_month)
                )
            }
            Text(text = state.selectedMonth.getDisplayName(TextStyle.SHORT, Locale.getDefault()))
            IconButton(onClick = onNextMonthClicked, enabled = nextMonthAvailable) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = stringResource(strings.R.string.cd_date_picker_switch_to_previous_month)
                )
            }
        }
    }
    Row {
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
                    color = TripsTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
private fun rememberDateChooserSnapFlingBehavior(
    lazyListState: LazyListState,
    decayAnimationSpec: DecayAnimationSpec<Float> = exponentialDecay()
): FlingBehavior {
    return remember(decayAnimationSpec, lazyListState) {
        val original = SnapLayoutInfoProvider(lazyListState)
        val snapLayoutInfoProvider = object : SnapLayoutInfoProvider by original {
            override fun calculateApproachOffset(initialVelocity: Float): Float {
                return 0.0f
            }
        }

        SnapFlingBehavior(
            snapLayoutInfoProvider = snapLayoutInfoProvider,
            decayAnimationSpec = decayAnimationSpec,
            snapAnimationSpec = spring(stiffness = Spring.StiffnessMediumLow)
        )
    }
}

@Preview
@Composable
private fun CalendarDateChooserPreview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            val currentDate = remember {
                Clock.System.now().toLocalDateTime(TimeZone.UTC).date
            }
            CalendarDateChooser(
                state = rememberCalendarState(
                    initialDate = currentDate,
                    calendarRange = CalendarRange(
                        startDate = LocalDate(1900, 1, 1),
                        endDate = currentDate,
                    )
                ),
                onDaySelected = {},
            )
        }
    }
}
