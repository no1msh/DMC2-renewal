package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.common.CustomResult
import com.monthlycoding.domain.model.Inquiry

interface InquiryDataSource {
    suspend fun postInquiry(inquiry: Inquiry): CustomResult<Unit>
}
