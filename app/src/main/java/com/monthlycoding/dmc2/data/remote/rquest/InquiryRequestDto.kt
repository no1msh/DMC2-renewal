package com.monthlycoding.dmc2.data.remote.rquest

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InquiryRequestDto(
    @SerialName("content")
    val content: String,
    @SerialName("email")
    val email: String,
    @SerialName("inquiryTypeId")
    val inquiryTypeId: Int
)
