package com.midnight.core.helper

sealed class DataState<out T> {
    object Loading: DataState<Nothing>()
    data class Success<out T>(val value: T): DataState<T>()
    data class LocalError(val message: String? = null): DataState<Nothing>()
    data class NetworkError(val networkError: NetworkErrorType?): DataState<Nothing>()
}