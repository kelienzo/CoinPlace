package com.kelly.coinplace.common

sealed class ResultHandler<out T> {
    object Loading : ResultHandler<Nothing>()
    data class Success<out T>(val data: T) : ResultHandler<T>()
    data class Error<T>(val errorData: T) : ResultHandler<T>()
    data class Exception(val throwable: Throwable) : ResultHandler<Nothing>()
}
