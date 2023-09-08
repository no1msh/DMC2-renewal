package com.monthlycoding.dmc2.data.mapper

import com.monthlycoding.dmc2.data.remote.response.FoodRecommendDto
import com.monthlycoding.dmc2.presenter.foodrecommend.model.Cuisine
import com.monthlycoding.dmc2.presenter.foodrecommendcards.model.FoodRecommendUiModel
import com.monthlycoding.dmc2.presenter.schoolaroundmap.model.CuisineMarker
import com.monthlycoding.domain.model.FoodRecommend
import com.naver.maps.geometry.LatLng

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
    requiredTime,
)

fun FoodRecommendUiModel.toCuisineMarker() = CuisineMarker(
    storeId = storeId,
    latLng = LatLng(latitude.toDouble(), longitude.toDouble()),
    storeName = storeName,
    icon = Cuisine.findById(categoryId).icon,
    address = address,
    closedDays = closedDays,
    naverLink = naverLink,
    operationHours = operationHours,
    requiredTime = requiredTime
)
