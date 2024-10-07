package com.nikitakrapo.birthdays.utils.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

suspend fun <T> onIo(action: suspend () -> T) : T = withContext(Dispatchers.IO) {
    return@withContext action()
}
