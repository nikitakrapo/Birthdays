package com.nikitakrapo.birthdays.network.result

suspend fun <T : Any> wrapWithNetworkResult(action: suspend () -> TripsBackendResponse<T>): NetworkResult<T> {
    return try {
        val result = action().result
        if (result == null) {
            NetworkResult.Error(Exception("Backend error"))
        } else {
            NetworkResult.Success(result)
        }
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

fun <T : Any, R : Any> NetworkResult<T>.map(mapper: (T) -> R): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success(mapper(data))
        is NetworkResult.Error -> NetworkResult.Error(exception)
    }
}

fun <T : Any> NetworkResult<T>.getDataOrNull(): T? = (this as? NetworkResult.Success)?.data

fun <T : Any> NetworkResult<T>.toResult(): Result<T> = when (this) {
    is NetworkResult.Success -> Result.success(data)
    is NetworkResult.Error -> Result.failure(exception)
}
