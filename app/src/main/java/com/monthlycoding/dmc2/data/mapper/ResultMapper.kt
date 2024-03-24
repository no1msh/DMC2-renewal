package com.monthlycoding.dmc2.data.mapper

import com.monthlycoding.dmc2.common.CustomResult
import java.io.IOException

fun <T, R> CustomResult<*>.toResult(toDomain: (T) -> R): Result<R> {
    return when (this) {
        is CustomResult.Success -> Result.success(toDomain(this.data as T))
        is CustomResult.ApiError -> Result.failure(IOException("[Error code: ${this.responseCode}] ${this.error}"))
        is CustomResult.NetworkError -> Result.failure(this.exception)
        is CustomResult.UnexpectedError -> Result.failure(this.exception)
    }
}
