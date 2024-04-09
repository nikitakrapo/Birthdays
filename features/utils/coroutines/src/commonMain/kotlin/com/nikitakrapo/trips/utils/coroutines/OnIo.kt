package com.nikitakrapo.trips.utils.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> onIo(action: suspend () -> T) : T = withContext(Dispatchers.IO) {
    return@withContext action()
}