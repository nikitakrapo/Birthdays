package com.nikitakrapo.birthdays.wizard

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.trips.design.theme.TripsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WizardScreen(
    modifier: Modifier = Modifier,
    component: WizardComponent,
) {
    val state by component.state.collectAsState()

    val selectableDates = remember(state.selectableDateTimes) {
        DatetimeSelectableDates(state.selectableDateTimes)
    }
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val isWideEnoughForPicker = maxWidth >= 360.dp
        val datePickerState = rememberDatePickerState(
            selectableDates = selectableDates,
            yearRange = state.yearRange,
            initialDisplayMode = if (isWideEnoughForPicker) {
                DisplayMode.Picker
            } else {
                DisplayMode.Input
            }
        )
        DatePicker(
            state = datePickerState,
            title = null,
            showModeToggle = isWideEnoughForPicker,
        )
    }
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