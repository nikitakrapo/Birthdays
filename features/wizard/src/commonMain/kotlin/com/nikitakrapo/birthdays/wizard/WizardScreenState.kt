package com.nikitakrapo.birthdays.wizard

import kotlinx.datetime.LocalDate

data class WizardScreenState(
    val initialDate: LocalDate,
    val yearRange: IntRange,
)
