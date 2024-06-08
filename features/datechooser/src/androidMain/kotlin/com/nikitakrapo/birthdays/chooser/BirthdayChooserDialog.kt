package com.nikitakrapo.birthdays.chooser

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nikitakrapo.birthdays.components.calendar.DateChooserDefaults
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun DateChooserDialogComponent.rememberDateChooserComponent(): DateChooserComponent {
    return remember { createDateChooserComponent() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayChooserDialog(
    modifier: Modifier = Modifier,
    component: DateChooserDialogComponent,
) {
    val state by component.state.collectAsState()

    DatePickerDialog(
        modifier = modifier.width(DateChooserDefaults.MediumWidth),
        onDismissRequest = component::onDismiss,
        confirmButton = {
            TextButton(
                onClick = component::onBirthdayConfirmed,
                enabled = state.chosenDate != null,
            ) {
                Text(text = stringResource(strings.R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = component::onDismiss) {
                Text(text = stringResource(strings.R.string.dismiss))
            }
        },
    ) {
        val dateChooserComponent = component.rememberDateChooserComponent()
        BirthdayChooserContent(
            modifier = Modifier
                .padding(8.dp),
            component = dateChooserComponent,
        )
    }
}

@Preview
@Composable
private fun BirthdayChooserDialogPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            BirthdayChooserDialog(
                component = DateChooserDialogComponentPreview,
            )
        }
    }
}
