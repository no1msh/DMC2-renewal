package com.monthlycoding.domain.model

data class FoodRecommend(
    val categoryId: Int,
    val address: String,
    val closedDays: String,
    val latitude: String,
    val longitude: String,
    val naverLink: String,
    val operationHours: String,
    val storeId: Int,
    val storeName: String,
    val requiredTime: String,
)
