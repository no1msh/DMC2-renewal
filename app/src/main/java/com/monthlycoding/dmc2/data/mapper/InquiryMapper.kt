package com.monthlycoding.dmc2.data.mapper

import com.monthlycoding.dmc2.data.remote.rquest.InquiryRequestDto
import com.monthlycoding.domain.model.Inquiry

fun Inquiry.toRequestDto(): InquiryRequestDto = InquiryRequestDto(
    content, email, inquiryTypeId
)
