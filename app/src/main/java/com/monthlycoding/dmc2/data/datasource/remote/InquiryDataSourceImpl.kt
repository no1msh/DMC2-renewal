package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.data.mapper.toRequestDto
import com.monthlycoding.dmc2.data.remote.api.InquiryService
import com.monthlycoding.domain.model.Inquiry

class InquiryDataSourceImpl(
    private val inquiryService: InquiryService
) : InquiryDataSource {
    override suspend fun postInquiry(inquiry: Inquiry) {
        inquiryService.postInquiry(inquiry.toRequestDto())
    }
}
