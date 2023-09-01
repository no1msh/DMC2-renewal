package com.monthlycoding.dmc2.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodRecommendDto(
    @SerialName("category_id")
    val categoryId: Int,
    @SerialName("address")
    val address: String,
    @SerialName("closed_days")
    val closedDays: String,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("naver_link")
    val naverLink: String,
    @SerialName("operation_hours")
    val operationHours: String,
    @SerialName("store_id")
    val storeId: Int,
    @SerialName("store_name")
    val storeName: String,
    @SerialName("required_time")
    val requiredTime: String,
)
