package com.monthlycoding.domain.repository

import com.monthlycoding.domain.model.Inquiry

interface InquiryRepository {
    suspend fun postInquiry(inquiry: Inquiry): Result<Unit>
}
