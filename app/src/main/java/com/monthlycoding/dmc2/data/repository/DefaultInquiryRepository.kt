package com.monthlycoding.dmc2.data.repository

import com.monthlycoding.dmc2.data.datasource.remote.InquiryDataSource
import com.monthlycoding.domain.model.Inquiry
import com.monthlycoding.domain.repository.InquiryRepository
import javax.inject.Inject

class DefaultInquiryRepository @Inject constructor(
    private val inquiryDataSource: InquiryDataSource
) : InquiryRepository {
    override suspend fun postInquiry(inquiry: Inquiry): Result<Unit> {
        return inquiryDataSource.postInquiry(inquiry)
    }
}
