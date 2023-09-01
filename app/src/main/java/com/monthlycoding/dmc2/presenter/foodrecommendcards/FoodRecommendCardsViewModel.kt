package com.monthlycoding.dmc2.presenter.foodrecommendcards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.monthlycoding.dmc2.data.datasource.remote.FoodRecommendDataSourceImpl
import com.monthlycoding.dmc2.data.remote.NetworkServiceModule
import com.monthlycoding.dmc2.data.repository.FoodRecommendRepositoryImpl
import com.monthlycoding.dmc2.presenter.foodrecommendcards.model.FoodRecommendUiModel
import com.monthlycoding.domain.model.FoodRecommend
import com.monthlycoding.domain.repository.FoodRecommendRepository
import kotlinx.coroutines.launch

class FoodRecommendCardsViewModel(
    private val foodRecommendRepository: FoodRecommendRepository
) : ViewModel() {
    private val _foodRecommends: MutableLiveData<List<FoodRecommendUiModel>> = MutableLiveData()
    val foodRecommends: LiveData<List<FoodRecommendUiModel>> get() = _foodRecommends

    fun fetchFoodRecommends(categoryIds: List<Int>) {
        viewModelScope.launch {
            runCatching {
                foodRecommendRepository.getFoodRecommends(categoryIds).map { it.toUiModel() }
                    .shuffled()
            }.onSuccess {
                _foodRecommends.value = it
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

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = FoodRecommendRepositoryImpl(
                    FoodRecommendDataSourceImpl(
                        NetworkServiceModule.foodRecommendService,
                    ),
                )
                return FoodRecommendCardsViewModel(repository) as T
            }
        }
    }
}
