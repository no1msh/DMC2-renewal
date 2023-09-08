package com.monthlycoding.dmc2.presenter.schoolaroundmap.model

import androidx.annotation.DrawableRes
import com.naver.maps.geometry.LatLng

data class CuisineMarker(
    val storeId: Int,
    val latLng: LatLng,
    val storeName: String,
    @DrawableRes val icon: Int,
    val address: String,
    val closedDays: String,
    val naverLink: String,
    val operationHours: String,
    val requiredTime: String,
)
