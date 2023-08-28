package com.monthlycoding.dmc2.data.mapper

import com.monthlycoding.dmc2.data.remote.response.FoodRecommendDto
import com.monthlycoding.domain.model.FoodRecommend

fun FoodRecommendDto.toDomain() = FoodRecommend(
    categoryId,
    address,
    closedDays,
    latitude,
    longitude,
    naverLink,
    operationHours,
    storeId,
    storeName,
)
