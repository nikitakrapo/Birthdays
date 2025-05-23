package com.nikitakrapo.birthdays

import com.arkivanov.decompose.ComponentContext

class AddBirthdayComponentImpl(
    componentContext: ComponentContext,
) : AddBirthdayComponent, ComponentContext by componentContext {
}