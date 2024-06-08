package com.nikitakrapo.birthdays.feed

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BirthdaysFeedComponentImpl(
    componentContext: ComponentContext,
) : BirthdaysFeedComponent, ComponentContext by componentContext {

    private val stateFlow: MutableStateFlow<BirthdaysFeedScreenState> = MutableStateFlow(BirthdaysFeedScreenState.Loading)
    override val state = stateFlow.asStateFlow()
}