package com.nikitakrapo.birthdays.feed

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.birthdays.di.Di
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.repositories.birthdays.BirthdaysRepository
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class BirthdaysFeedComponentImpl(
    componentContext: ComponentContext,
) : BirthdaysFeedComponent, ComponentContext by componentContext {

    private val coroutineScope = coroutineScope()

    private val birthdaysRepository by Di.inject<BirthdaysRepository>()

    private val stateFlow: MutableStateFlow<BirthdaysFeedScreenState> =
        MutableStateFlow(BirthdaysFeedScreenState.Loading)
    override val state = stateFlow.asStateFlow()

    init {
        fetchBirthdays()
    }

    override fun onDateSelected(date: LocalDate) {
        val birthdays = (state.value as? BirthdaysFeedScreenState.Loaded)?.birthdays ?: return
    }

    override fun onBirthdayClicked(birthday: Birthday) {
        TODO("Not yet implemented")
    }

    private fun fetchBirthdays() {
        coroutineScope.launch {
            val birthdaysResult = birthdaysRepository.getBirthdays()
            birthdaysResult.fold(
                onSuccess = {
                    stateFlow.value = BirthdaysFeedScreenState.Loaded(
                        initialDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
                        startDate = LocalDate(2024, 1, 1),
                        endDate = LocalDate(2026, 1, 1),
                        birthdays = it,
                    )
                },
                onFailure = { TODO() },
            )
        }
    }
}