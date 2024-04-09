package com.nikitakrapo.trips.network.result

suspend fun <T : Any> wrapWithNetworkResult(action: suspend () -> T): NetworkResult<T> {
    return try {
        val result = action()
        NetworkResult.Success(result)
    } catch (e: Exception) {
        NetworkResult.Error(e)
    }
}

fun <T : Any> NetworkResult<T>.onSuccess(action: (T) -> Unit) {
    if (this is NetworkResult.Success) {
        action(data)
    }
}

fun <T : Any> NetworkResult<T>.onError(action: (Exception) -> Unit) {
    if (this is NetworkResult.Error) {
        action(exception)
    }
}

fun <T : Any> NetworkResult<T>.fold(
    onSuccess: (T) -> Unit,
    onError: (Exception) -> Unit,
) {
    when (this) {
        is NetworkResult.Success -> onSuccess(data)
        is NetworkResult.Error -> onError(exception)
    }
}
