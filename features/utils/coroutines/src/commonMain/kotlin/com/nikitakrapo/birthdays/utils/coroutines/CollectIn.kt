package com.nikitakrapo.birthdays.utils.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectIn(
    scope: CoroutineScope,
    collector: FlowCollector<T>,
): Job {
    return scope.launch {
        collect(collector)
    }
}