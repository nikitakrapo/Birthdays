package com.nikitakrapo.birthdays.wizard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.birthdays.chooser.BirthdayChooserDialog
import com.nikitakrapo.birthdays.theme.BirthdaysTheme

@Composable
fun WizardScreen(
    component: WizardComponent,
    modifier: Modifier = Modifier,
) {
    val dialogSlot by component.dialogSlot.collectAsState()

    WizardMainScreen(
        modifier = modifier
            .fillMaxSize(),
        component = component,
    )

    when (val instance = dialogSlot.child?.instance) {
        is WizardComponent.WizardDialog.BirthdayChooser -> BirthdayChooserDialog(
            component = instance.component,
        )
        null -> {}
    }
}

@Preview
@Composable
private fun WizardScreenPreview() {
    BirthdaysTheme {
        Surface(color = BirthdaysTheme.colorScheme.background) {
            WizardScreen(component = WizardComponentPreview)
        }
    }
}