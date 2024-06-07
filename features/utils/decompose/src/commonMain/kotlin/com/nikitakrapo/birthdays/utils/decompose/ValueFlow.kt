package com.nikitakrapo.birthdays.utils.decompose

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

fun <T : Any> Value<T>.asStateFlow(): StateFlow<T> {
    val mutableStackFlow = MutableStateFlow(value)
    subscribe { mutableStackFlow.value = it }
    return mutableStackFlow.asStateFlow()
}