package com.nikitakrapo.birthdays.wizard

import kotlinx.datetime.LocalDate

data class WizardScreenState(
    val screens: List<WizardScreen>,
    val lastAvailableScreen: Int,
    val birthday: LocalDate?,
) {
    val isBirthdaySelected = birthday != null

    enum class WizardScreen {
        Landing,
        BirthdayChooser,
        Final
        ;
    }
}
