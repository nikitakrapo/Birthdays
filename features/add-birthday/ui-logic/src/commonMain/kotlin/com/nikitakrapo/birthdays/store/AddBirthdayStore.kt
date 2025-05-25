package com.nikitakrapo.birthdays.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.nikitakrapo.birthdays.AddBirthdayState
import com.nikitakrapo.birthdays.network.result.NetworkResult
import com.nikitakrapo.birthdays.repositories.birthdays.BirthdaysRepository
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal sealed interface Intent {

    data class ChangeNameText(val text: String) : Intent

    data class ChangeDate(val date: LocalDate) : Intent

    data object AddBirthday : Intent
}

internal sealed interface Label {

    data object BirthdayAddSucceeded : Label

    data object BirthdayAddFailed : Label
}

private sealed interface Action {
    // ...
}

private sealed interface Msg {

    data class NameTextChanged(val text: String) : Msg

    data class DateChanged(val date: LocalDate) : Msg
}

internal fun AddBirthdayStore(
    repository: BirthdaysRepository,
    storeFactory: StoreFactory,
): Store<Intent, AddBirthdayState, Label> =
    storeFactory.create(
        name = "AddBirthdayStore",
        initialState = AddBirthdayState(
            nameText = "",
            date = null,
            isAddActive = false,
            yearRange = 1900..Clock.System.now().toLocalDateTime(TimeZone.UTC).year,
            lastSelectableDatetimeMs = Clock.System.now().toEpochMilliseconds(),
        ),
        executorFactory = {
            ExecutorImpl(
                repository = repository,
            )
        },
        reducer = ReducerImpl,
    )

private class ExecutorImpl(
    private val repository: BirthdaysRepository,
) : CoroutineExecutor<Intent, Action, AddBirthdayState, Msg, Label>() {
    override fun executeIntent(intent: Intent) {
        when (intent) {
            is Intent.ChangeNameText -> dispatch(Msg.NameTextChanged(intent.text))
            is Intent.ChangeDate -> dispatch(Msg.DateChanged(intent.date))
            Intent.AddBirthday -> {
                scope.launch {
                    val state = state()
                    state.date ?: return@launch
                    val result = repository.addBirthday(state.nameText, state.date)
                    when (result) {
                        is NetworkResult.Success -> publish(Label.BirthdayAddSucceeded)
                        is NetworkResult.Error -> publish(Label.BirthdayAddFailed)
                    }
                }
            }
        }
    }
}

private object ReducerImpl : Reducer<AddBirthdayState, Msg> {
    override fun AddBirthdayState.reduce(msg: Msg): AddBirthdayState {
        return when (msg) {
            is Msg.NameTextChanged -> copy(
                nameText = msg.text,
                isAddActive = msg.text.isNotBlank() && date != null
            )
            is Msg.DateChanged -> copy(
                date = msg.date,
                isAddActive = nameText.isNotBlank()
            )
        }
    }
}