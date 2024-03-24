package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.common.CustomResult
import com.monthlycoding.dmc2.data.mapper.toRequestDto
import com.monthlycoding.dmc2.data.remote.api.InquiryService
import com.monthlycoding.domain.model.Inquiry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkInquiryDataSource @Inject constructor(
    private val inquiryService: InquiryService
) : InquiryDataSource {
    override suspend fun postInquiry(inquiry: Inquiry): CustomResult<Unit> {
        return inquiryService.postInquiry(inquiry.toRequestDto())
    }
}
