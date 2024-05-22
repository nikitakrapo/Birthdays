package com.nikitakrapo.birthdays.components.calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputDateChooser(
    modifier: Modifier = Modifier,
) {
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
    )
    DatePicker(
        state = datePickerState,
        title = null,
        headline = null,
        showModeToggle = false,
    )
}

@Preview
@Composable
private fun TextInputDateChooserPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            Box(modifier = Modifier.padding(100.dp)) {
                TextInputDateChooser()
            }
        }
    }
}
