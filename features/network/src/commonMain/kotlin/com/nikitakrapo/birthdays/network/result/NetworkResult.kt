package com.nikitakrapo.birthdays.network.result

sealed interface NetworkResult<out T : Any> {

    class Success<T : Any>(
        val data: T,
    ) : NetworkResult<T>

    class Error(
        val exception: Exception,
    ) : NetworkResult<Nothing>
}