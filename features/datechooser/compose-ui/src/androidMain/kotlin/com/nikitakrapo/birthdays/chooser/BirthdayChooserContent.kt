package com.nikitakrapo.birthdays.chooser

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.EditCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.chooser.DateChooserState.ChooserMode
import com.nikitakrapo.birthdays.components.calendar.Calendar
import com.nikitakrapo.birthdays.components.calendar.DateChooserDefaults
import com.nikitakrapo.birthdays.components.calendar.data.CalendarRange
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarState
import com.nikitakrapo.birthdays.components.calendar.info.DateInfoProvider
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun BirthdayChooserContent(
    modifier: Modifier = Modifier,
    component: DateChooserComponent,
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier
            .width(DateChooserDefaults.MediumWidth),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        BirthdayChooserHeader(
            modifier = Modifier
                .fillMaxWidth(),
            title = state.title,
            mode = state.mode,
            onChooserModeSelected = null,
        )
        Spacer(modifier = Modifier.height(8.dp))
        val calendarState = rememberCalendarState(
            initialDate = state.initialDate,
            initialSelectedDate = state.initialSelectedDate,
            calendarRange = CalendarRange(
                startDate = state.startDate,
                endDate = state.endDate,
            ),
        )
        Calendar(
            state = calendarState,
            yearPickerEnabled = true,
            onDaySelected = component::onDatePicked,
            dateInfoProvider = DateInfoProvider.empty(),
        )
    }
}

@Composable
private fun BirthdayChooserHeader(
    modifier: Modifier = Modifier,
    title: String,
    mode: ChooserMode,
    onChooserModeSelected: ((ChooserMode) -> Unit)?,
) {
    Row(
        modifier = modifier
            .padding(start = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = BirthdaysTheme.typography.titleLarge,
        )
        onChooserModeSelected?.let {
            IconButton(
                onClick = {
                    when (mode) {
                        ChooserMode.Calendar -> onChooserModeSelected(ChooserMode.Text)
                        ChooserMode.Text -> onChooserModeSelected(ChooserMode.Calendar)
                    }
                },
            ) {
                Icon(
                    imageVector = when (mode) {
                        ChooserMode.Calendar ->
                            Icons.Outlined.EditCalendar

                        ChooserMode.Text ->
                            Icons.Outlined.Edit
                    },
                    contentDescription = when (mode) {
                        ChooserMode.Calendar ->
                            stringResource(strings.R.string.cd_birthday_chooser_calendar_mode)

                        ChooserMode.Text ->
                            stringResource(strings.R.string.cd_birthday_chooser_text_mode)
                    },
                )
            }
        }
    }
}

@Preview
@Composable
private fun BirthdayChooserContentPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            BirthdayChooserContent(
                component = DateChooserComponentPreview,
            )
        }
    }
}