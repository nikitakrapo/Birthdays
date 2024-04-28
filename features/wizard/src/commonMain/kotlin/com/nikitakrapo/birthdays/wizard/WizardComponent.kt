package com.nikitakrapo.birthdays.wizard

import kotlinx.coroutines.flow.StateFlow

interface WizardComponent {

    val state: StateFlow<WizardScreenState>
}