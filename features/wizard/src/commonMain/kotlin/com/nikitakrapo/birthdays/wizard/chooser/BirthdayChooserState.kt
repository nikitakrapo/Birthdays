package com.nikitakrapo.birthdays.wizard.chooser

import kotlinx.datetime.LocalDate

data class BirthdayChooserState(
    val chosenDate: LocalDate?,
    val yearRange: IntRange,
)
