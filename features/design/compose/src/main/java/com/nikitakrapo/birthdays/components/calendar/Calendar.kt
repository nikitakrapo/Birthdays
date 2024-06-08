package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.data.CalendarRange
import com.nikitakrapo.birthdays.components.calendar.data.CalendarState
import com.nikitakrapo.birthdays.components.calendar.data.getAbsoluteMonthForYear
import com.nikitakrapo.birthdays.components.calendar.data.getMonthFromAbsoluteMonth
import com.nikitakrapo.birthdays.components.calendar.data.getMonthState
import com.nikitakrapo.birthdays.components.calendar.data.getYearFromAbsoluteMonth
import com.nikitakrapo.birthdays.components.calendar.data.numberOfMonths
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarLazyListState
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarState
import com.nikitakrapo.birthdays.components.calendar.info.DateInfoProvider
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.toLocalDateTime
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    state: CalendarState,
    yearPickerEnabled: Boolean,
    dateInfoProvider: DateInfoProvider,
    onDaySelected: (LocalDate) -> Unit,
) {
    LaunchedEffect(state.selectedDate) {
        state.selectedDate?.let { onDaySelected(it) }
    }
    Column(
        modifier = modifier
            .width(DateChooserDefaults.MediumWidth),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val scope = rememberCoroutineScope()
        val monthLazyListState = rememberCalendarLazyListState(state)
        var yearSelectorShowed by remember { mutableStateOf(false) }
        MonthHeader(
            state = state,
            onNextMonthClicked = {
                scope.launch {
                    val index = (monthLazyListState.firstVisibleItemIndex + 1).coerceAtMost(state.numberOfMonths - 1)
                    monthLazyListState.animateScrollToItem(index)
                }
            },
            onPreviousMonthClicked = {
                scope.launch {
                    val index = (monthLazyListState.firstVisibleItemIndex - 1).coerceAtLeast(0)
                    monthLazyListState.animateScrollToItem(index)
                }
            },
            nextMonthAvailable = monthLazyListState.canScrollForward,
            previousMonthAvailable = monthLazyListState.canScrollBackward,
            showMonthsNavigation = !yearSelectorShowed,
            onSelectYearClicked = { yearSelectorShowed = !yearSelectorShowed },
            yearSelectorShowed = yearSelectorShowed,
            enableYearPicker = yearPickerEnabled,
        )
        AnimatedContent(targetState = yearSelectorShowed) { showSelector ->
            if (showSelector) {
                val years = remember(state.calendarRange) {
                    state.calendarRange
                        .let { it.startDate.year..it.endDate.year }
                        .toList()
                }
                val lazyGridState = rememberLazyGridState(
                    initialFirstVisibleItemIndex = state.getAbsoluteMonthForYear(state.selectedYear)
                )
                LazyVerticalGrid(
                    modifier = Modifier
                        .height(48.dp * 7),
                    columns = GridCells.Fixed(3),
                    state = lazyGridState,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(years) { year ->
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(BirthdaysTheme.colorScheme.surfaceContainer)
                                .clickable(onClick = {
                                    scope.launch {
                                        val monthToScroll = state.getAbsoluteMonthForYear(year)
                                        monthLazyListState.scrollToItem(monthToScroll)
                                    }
                                    yearSelectorShowed = false
                                })
                                .padding(8.dp)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(text = year.toString())
                        }
                    }
                }
            } else {
                Column {
                    WeekdaysNames()
                    MonthsPager(
                        state = state,
                        monthLazyListState = monthLazyListState,
                        dateInfoProvider = dateInfoProvider,
                    )
                }
            }
        }
    }
}

@Composable
private fun MonthHeader(
    state: CalendarState,
    onNextMonthClicked: () -> Unit,
    onPreviousMonthClicked: () -> Unit,
    nextMonthAvailable: Boolean,
    previousMonthAvailable: Boolean,
    showMonthsNavigation: Boolean,
    onSelectYearClicked: () -> Unit,
    yearSelectorShowed: Boolean,
    enableYearPicker: Boolean,
) {
    Spacer(modifier = Modifier.height(4.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .minimumInteractiveComponentSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onSelectYearClicked)
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val dateText = buildString {
                append(state.selectedMonth.getDisplayName(TextStyle.SHORT, Locale.getDefault()))
                if (!enableYearPicker) return@buildString
                append(" ")
                append(state.selectedYear)
            }
            Text(
                text = dateText,
            )
            if (enableYearPicker) {
                val rotation by animateFloatAsState(targetValue = if (yearSelectorShowed) 180f else 0f)
                Icon(
                    modifier = Modifier
                        .rotate(rotation),
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = stringResource(strings.R.string.cd_date_picker_choose_year)
                )
            }
        }
        Spacer(modifier = Modifier.width(1.dp))
        AnimatedVisibility(visible = showMonthsNavigation) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onPreviousMonthClicked, enabled = previousMonthAvailable) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(strings.R.string.cd_date_picker_switch_to_next_month)
                    )
                }
                IconButton(onClick = onNextMonthClicked, enabled = nextMonthAvailable) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = stringResource(strings.R.string.cd_date_picker_switch_to_previous_month)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun WeekdaysNames() {
    Row {
        // TODO TRIPSMOBILE-12: add localization
        DayOfWeekNames.ENGLISH_ABBREVIATED.names.forEach {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .semantics { invisibleToUser() },
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = it.first().toString(),
                    style = BirthdaysTheme.typography.titleMedium,
                    color = BirthdaysTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
private fun MonthsPager(
    state: CalendarState,
    monthLazyListState: LazyListState,
    dateInfoProvider: DateInfoProvider,
) {
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
            val monthState = getMonthState(
                year = currentYear,
                month = currentMonth,
                selectedDay = selectedDay,
                dayRange = (firstDay ?: 0)..(lastDay ?: 31),
            )
            CalendarMonth(
                modifier = Modifier
                    .fillParentMaxWidth(),
                month = monthState,
                dateInfoProvider = dateInfoProvider,
                onDayClicked = {
                    val localDate = LocalDate(currentYear, currentMonth, it.day.dayOfMonth)
                    state.selectedDate = localDate
                },
            )
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
private fun CalendarPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            val currentDate = remember {
                Clock.System.now().toLocalDateTime(TimeZone.UTC).date
            }
            Calendar(
                state = rememberCalendarState(
                    initialDate = currentDate,
                    initialSelectedDate = null,
                    calendarRange = CalendarRange(
                        startDate = LocalDate(1900, 1, 1),
                        endDate = currentDate,
                    )
                ),
                yearPickerEnabled = true,
                onDaySelected = {},
                dateInfoProvider = DateInfoProvider.empty(),
            )
        }
    }
}
