package com.monthlycoding.dmc2.data.repository

import com.monthlycoding.dmc2.data.datasource.remote.InquiryDataSource
import com.monthlycoding.domain.model.Inquiry
import com.monthlycoding.domain.repository.InquiryRepository

class InquiryRepositoryImpl(
    private val inquiryDataSource: InquiryDataSource
) : InquiryRepository {
    override suspend fun postInquiry(inquiry: Inquiry) {
        inquiryDataSource.postInquiry(inquiry)
    }
}
