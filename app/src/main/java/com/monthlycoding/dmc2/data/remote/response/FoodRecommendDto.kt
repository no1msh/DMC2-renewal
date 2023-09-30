package com.monthlycoding.dmc2.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodRecommendDto(
    @SerialName("categoryId")
    val categoryId: Int,
    @SerialName("address")
    val address: String,
    @SerialName("closedDays")
    val closedDays: String,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("naverLink")
    val naverLink: String,
    @SerialName("operationHours")
    val operationHours: String,
    @SerialName("storeId")
    val storeId: Int,
    @SerialName("storeName")
    val storeName: String,
    @SerialName("requiredTime")
    val requiredTime: String,
)
