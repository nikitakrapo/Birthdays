package com.nikitakrapo.birthdays

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.buttons.BackButton
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBirthdayScreen(
    component: AddBirthdayComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.collectAsState()

    Box(
        modifier = modifier,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
        ) {
            TopAppBar(
                title = {},
                navigationIcon = {
                    BackButton(
                        onClick = component::onBackClicked
                    )
                }
            )
            TextField(
                label = {
                    Text(text = stringResource(strings.R.string.name_label))
                },
                value = state.nameText,
                onValueChange = component::onNameTextChanged,
                maxLines = 1,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))

            var datePickerShown by remember { mutableStateOf(false) }
            val datePickerState = rememberDatePickerState(
                initialDisplayMode = DisplayMode.Input,
                selectableDates = object : SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        return utcTimeMillis <= state.lastSelectableDatetimeMs
                    }

                    override fun isSelectableYear(year: Int): Boolean {
                        return year in state.yearRange
                    }
                }
            )

            val source = remember { MutableInteractionSource() }
            TextField(
                label = {
                    Text(text = stringResource(strings.R.string.birthday_date_label))
                },
                value = state.dateText ?: "",
                onValueChange = {},
                readOnly = true,
                interactionSource = source,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
            )

            if (source.collectIsPressedAsState().value) {
                datePickerShown = true
            }

            if (datePickerShown) {
                DatePickerDialog(
                    onDismissRequest = { datePickerShown = false },
                    confirmButton = {
                        TextButton(onClick = {
                            val selectedDateMillis = datePickerState.selectedDateMillis ?: return@TextButton
                            datePickerShown = false
                            component.onDateChanged(selectedDateMillis)
                        }) {
                            Text(text = stringResource(strings.R.string.confirm))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { datePickerShown = false }) {
                            Text(text = stringResource(strings.R.string.cancel))
                        }
                    }
                ) {
                    DatePicker(state = datePickerState)
                }
            }
        }

        FloatingActionButton(
            onClick = component::onAddClicked,
            content = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = stringResource(strings.R.string.cd_add_birthday),
                )
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        )
    }
}

@Preview(device = "spec:width=411dp,height=891dp")
@Composable
private fun AddBirthdayScreenPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            AddBirthdayScreen(
                component = AddBirthdayComponentPreview(),
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}
