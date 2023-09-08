package com.monthlycoding.dmc2.presenter.schoolaroundmap.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.naver.maps.geometry.LatLng
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable
