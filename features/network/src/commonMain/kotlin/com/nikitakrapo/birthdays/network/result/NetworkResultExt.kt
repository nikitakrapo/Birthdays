package com.nikitakrapo.birthdays.network.result

import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.bodyAsText
import io.ktor.http.parsing.ParseException

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

suspend fun <T : Any> getNetworkResult(action: suspend () -> T): NetworkResult<T> {
    return try {
        val result = action()
        NetworkResult.Success(result)
    } catch (e: ResponseException) {
        val response = e.response
        val message = "${response.status}:\n${response.bodyAsText()}"
        NetworkResult.Error(exception = e, message = message)
    } catch (e: Exception) {
        NetworkResult.Error(exception = e)
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

inline fun <R, T : Any> NetworkResult<T>.fold(
    onSuccess: (T) -> R,
    onError: (Exception) -> R,
) : R {
    return when (this) {
        is NetworkResult.Success -> onSuccess(data)
        is NetworkResult.Error -> onError(exception)
    }
}

fun <T : Any, R : Any> NetworkResult<T>.map(mapper: (T) -> R?): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> {
            val mapResult: R? = mapper(data)
            if (mapResult != null) {
                NetworkResult.Success(mapResult)
            } else {
                NetworkResult.Error(ParseException("Unable to parse $data"))
            }
        }
        is NetworkResult.Error -> this
    }
}

fun <T : Any> NetworkResult<T>.getDataOrNull(): T? = (this as? NetworkResult.Success)?.data

fun <T : Any> NetworkResult<T>.getErrorOrNull(): Exception? = (this as? NetworkResult.Error)?.exception

fun <T : Any> NetworkResult<T>.toResult(): Result<T> = when (this) {
    is NetworkResult.Success -> Result.success(data)
    is NetworkResult.Error -> Result.failure(exception)
}
