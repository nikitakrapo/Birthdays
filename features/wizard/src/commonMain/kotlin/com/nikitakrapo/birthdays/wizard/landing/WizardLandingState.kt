package com.nikitakrapo.birthdays.wizard.landing

internal data class WizardLandingState(
    val username: String,
    val isBirthdayChosen: Boolean,
    val isCompletionAllowed: Boolean,
)