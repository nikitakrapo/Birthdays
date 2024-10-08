package com.nikitakrapo.birthdays.utils.decompose

import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext

fun <T : Any> Value<T>.asStateFlow(): StateFlow<T> {
    val mutableStackFlow = MutableStateFlow(value)
    subscribe { mutableStackFlow.value = it }
    return mutableStackFlow.asStateFlow()
}

fun <T : Any> StateFlow<T>.asValue(): Value<T> {
    val mutableValue = MutableValue(value)
    CoroutineScope(EmptyCoroutineContext).launch {
        collect {
            mutableValue.value = it
        }
    }
    return mutableValue
}