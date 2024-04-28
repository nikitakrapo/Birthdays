package com.nikitakrapo.birthdays.wizard

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.birthdays.components.calendar.CalendarDateChooser
import com.nikitakrapo.birthdays.components.calendar.data.rememberCalendarState
import com.nikitakrapo.trips.design.theme.TripsTheme

@Composable
fun WizardScreen(
    modifier: Modifier = Modifier,
    component: WizardComponent,
) {
    val state by component.state.collectAsState()

    val calendarState = rememberCalendarState(
        initialMonth = state.initialDate.month,
        initialYear = state.initialDate.year,
        initialDay = state.initialDate.dayOfMonth,
        yearRange = state.yearRange,
    )
    CalendarDateChooser(state = calendarState, onDaySelected = {})
}

@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",)
@Composable
private fun WizardScreenPreview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            WizardScreen(component = WizardComponentPreview)
        }
    }
}