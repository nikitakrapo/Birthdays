package com.nikitakrapo.birthdays.wizard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.birthdays.wizard.chooser.BirthdayChooserDialog
import com.nikitakrapo.trips.design.theme.TripsTheme

@Composable
fun WizardScreen(
    modifier: Modifier = Modifier,
    component: WizardComponent,
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

@Preview(device = "spec:id=reference_phone,shape=Normal,width=411,height=891,unit=dp,dpi=420",)
@Composable
private fun WizardScreenPreview() {
    TripsTheme {
        Surface(color = TripsTheme.colorScheme.background) {
            WizardScreen(component = WizardComponentPreview)
        }
    }
}