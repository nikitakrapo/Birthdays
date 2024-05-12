package com.nikitakrapo.birthdays.wizard.chooser

import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
internal fun BirthdayChooserComponent.rememberDateChooserComponent(): DateChooserComponent {
    return remember { createDateChooserComponent() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BirthdayChooserDialog(
    modifier: Modifier = Modifier,
    component: BirthdayChooserComponent,
) {
    val state by component.state.collectAsState()

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = component::onDismiss,
        confirmButton = {
            TextButton(
                onClick = component::onBirthdayConfirmed,
                enabled = state.chosenDate != null,
            ) {
                Text(text = stringResource(strings.R.string.confirm))
            }
        },
    ) {
        val dateChooserComponent = component.rememberDateChooserComponent()
        DateChooser(
            component = dateChooserComponent,
        )
    }
}