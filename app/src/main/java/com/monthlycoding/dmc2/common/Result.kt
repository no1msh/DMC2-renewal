package com.monthlycoding.dmc2.common

sealed class Result<out R> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success! Data=$data"
            is Error -> "Error! Error=$exception"
            is Loading -> "Loading..."
        }
    }
}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data
