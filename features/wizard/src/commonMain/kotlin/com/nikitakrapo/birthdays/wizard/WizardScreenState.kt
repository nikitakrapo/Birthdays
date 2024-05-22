package com.nikitakrapo.birthdays.wizard

import kotlinx.datetime.LocalDate

data class WizardScreenState(
    val birthday: LocalDate?,
) {
    val isBirthdaySelected = birthday != null
}
