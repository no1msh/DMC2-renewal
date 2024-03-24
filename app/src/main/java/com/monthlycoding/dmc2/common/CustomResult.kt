package com.monthlycoding.dmc2.common

import java.io.IOException

sealed class CustomResult<out T : Any> {
    data class Success<T : Any>(val data: T?) : CustomResult<T>()
    data class ApiError(val responseCode: Int, val error: String?) : CustomResult<Nothing>()
    data class NetworkError(val exception: IOException) : CustomResult<Nothing>()
    data class UnexpectedError(val exception: Throwable) : CustomResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success! Data=$data"
            is ApiError -> "Error! Response Code: $responseCode ErrorBody=$error"
            is NetworkError -> "NetworkError! $exception"
            is UnexpectedError -> "UnexpectedError! $exception"
        }
    }
}
