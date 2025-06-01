package com.nikitakrapo.birthdays

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.nikitakrapo.birthdays.repositories.birthdays.BirthdaysRepository
import com.nikitakrapo.birthdays.store.AddBirthdayStore
import com.nikitakrapo.birthdays.store.Intent
import com.nikitakrapo.birthdays.store.Label
import com.nikitakrapo.birthdays.utils.coroutines.collectIn
import com.nikitakrapo.birthdays.utils.decompose.coroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class AddBirthdayComponentImpl(
    componentContext: ComponentContext,
    private val repository: BirthdaysRepository,
    private val closeScreen: () -> Unit,
) : AddBirthdayComponent, ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val store = instanceKeeper.getStore("add_birthday_store") {
        AddBirthdayStore(
            repository = repository,
            storeFactory = DefaultStoreFactory(),
        )
    }

    init {
        store.labels.collectIn(scope) {
            when (it) {
                // TODO: handle labels correctly
                Label.BirthdayAddSucceeded -> closeScreen()
                Label.BirthdayAddFailed -> closeScreen()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<AddBirthdayState> = store.stateFlow

    override fun onNameTextChanged(text: String) {
        store.accept(Intent.ChangeNameText(text))
    }

    override fun onDateChanged(dateTimeMillis: Long) {
        val dateTime = Instant.fromEpochMilliseconds(dateTimeMillis).toLocalDateTime(TimeZone.UTC)
        val date = LocalDate(year = dateTime.year, month = dateTime.month, dayOfMonth = dateTime.dayOfMonth)
        store.accept(Intent.ChangeDate(date))
    }

    override fun onAddClicked() {
        store.accept(Intent.AddBirthday)
    }

    override fun onBackClicked() {
        closeScreen()
    }
}