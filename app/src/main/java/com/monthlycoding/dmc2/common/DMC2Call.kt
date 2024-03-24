package com.monthlycoding.dmc2.common

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class DMC2Call<T : Any>(private val call: Call<T>) : Call<CustomResult<T>> {
    override fun clone(): Call<CustomResult<T>> = DMC2Call(call.clone())

    override fun execute(): Response<CustomResult<T>> {
        throw UnsupportedOperationException("DMC2Call doesn't support execute")
    }

    override fun enqueue(callback: Callback<CustomResult<T>>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onResponse(
                        this@DMC2Call,
                        Response.success(CustomResult.Success(response.body()))
                    )
                    return
                }
                callback.onResponse(
                    this@DMC2Call,
                    Response.success(
                        CustomResult.ApiError(
                            responseCode = response.code(),
                            error = response.errorBody()?.string()
                        )
                    )
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResponse = when (t) {
                    is IOException -> CustomResult.NetworkError(t)
                    else -> CustomResult.UnexpectedError(t)
                }
                callback.onResponse(this@DMC2Call, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted(): Boolean = call.isExecuted

    override fun cancel() = call.cancel()

    override fun isCanceled(): Boolean = call.isCanceled

    override fun request(): Request = call.request()

    override fun timeout(): Timeout = call.timeout()
}
