package com.nikitakrapo.birthdays.chooser

import kotlinx.datetime.LocalDate

data class BirthdayChooserState(
    val chosenDate: LocalDate?,
    val yearRange: IntRange,
)
