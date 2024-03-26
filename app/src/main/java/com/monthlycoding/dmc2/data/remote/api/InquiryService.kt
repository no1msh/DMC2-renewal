package com.monthlycoding.dmc2.data.remote.api

import com.monthlycoding.dmc2.data.remote.rquest.InquiryRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface InquiryService {
    @POST("/inquiry")
    suspend fun postInquiry(
        @Body
        inquiryRequestDto: InquiryRequestDto,
    ): Result<Unit>
}
