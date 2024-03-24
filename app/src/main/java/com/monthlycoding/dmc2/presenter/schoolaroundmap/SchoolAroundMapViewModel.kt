package com.monthlycoding.dmc2.presenter.schoolaroundmap

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monthlycoding.dmc2.data.repository.DefaultFoodRecommendRepository
import com.monthlycoding.dmc2.presenter.foodrecommend.model.Cuisine
import com.monthlycoding.dmc2.presenter.schoolaroundmap.model.CuisineMarker
import com.monthlycoding.domain.model.FoodRecommend
import com.naver.maps.geometry.LatLng
import kotlinx.coroutines.launch

class SchoolAroundMapViewModel(
    private val repository: DefaultFoodRecommendRepository
) : ViewModel() {

    private val _cuisineMarkers: MutableLiveData<List<CuisineMarker>> = MutableLiveData()
    val cuisineMarkers: LiveData<List<CuisineMarker>> get() = _cuisineMarkers

    fun getStoreMarkers(ids: List<Int>) {
        viewModelScope.launch {
            repository.getFoodRecommends(ids)
                .onSuccess { foodRecommends ->
                    _cuisineMarkers.value = foodRecommends
                        .map { it.toCuisineMarker() }
                }
                .onFailure {
                    Log.d("NetworkError", it.message.toString())
                }
        }
    }

    private fun FoodRecommend.toCuisineMarker(): CuisineMarker = CuisineMarker(
        storeId = storeId,
        latLng = LatLng(latitude.toDouble(), longitude.toDouble()),
        storeName = storeName,
        icon = Cuisine.findById(categoryId).icon,
        address = address,
        closedDays = closedDays,
        naverLink = naverLink,
        operationHours = operationHours,
        requiredTime = requiredTime,
    )
}
