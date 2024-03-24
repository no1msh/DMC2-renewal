package com.monthlycoding.dmc2.common

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class DMC2CallAdapter<R : Any> private constructor(private val responseType: Type) :
    CallAdapter<R, Call<CustomResult<R>>> {
    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<CustomResult<R>> = DMC2Call(call)

    companion object CallAdapterFactory : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            if (getRawType(returnType) != Call::class.java) {
                return null
            }

            check(returnType is ParameterizedType) {
                "return type must be parameterized as Call<Result<Foo>> or Call<Result<out Foo>>"
            }

            val responseType: Type = getParameterUpperBound(0, returnType)

            if (getRawType(responseType) != CustomResult::class.java) {
                return null
            }

            check(responseType is ParameterizedType) {
                "Response must be parameterized as Result<Foo> or Result<out Foo>"
            }

            val successBodyType = getParameterUpperBound(0, responseType)

            return DMC2CallAdapter<Any>(successBodyType)
        }
    }
}
