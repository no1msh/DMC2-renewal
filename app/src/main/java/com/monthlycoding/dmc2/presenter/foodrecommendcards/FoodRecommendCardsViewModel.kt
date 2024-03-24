package com.monthlycoding.dmc2.presenter.foodrecommendcards

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monthlycoding.dmc2.presenter.foodrecommendcards.model.FoodRecommendUiModel
import com.monthlycoding.domain.model.FoodRecommend
import com.monthlycoding.domain.repository.FoodRecommendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodRecommendCardsViewModel @Inject constructor(
    private val foodRecommendRepository: FoodRecommendRepository
) : ViewModel() {
    private val _foodRecommends: MutableLiveData<List<FoodRecommendUiModel>> = MutableLiveData()
    val foodRecommends: LiveData<List<FoodRecommendUiModel>> get() = _foodRecommends

    fun fetchFoodRecommends(categoryIds: List<Int>) {
        viewModelScope.launch {
            foodRecommendRepository.getFoodRecommends(categoryIds)
                .onSuccess {
                    _foodRecommends.value = it.map { it.toUiModel() }
                }
                .onFailure {
                    Log.d("NetworkError", it.message.toString())
                }
        }
    }

    private fun FoodRecommend.toUiModel() = FoodRecommendUiModel(
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
}
