package com.monthlycoding.dmc2.presenter.foodrecommendcards

import com.monthlycoding.dmc2.presenter.foodrecommendcards.model.FoodRecommendUiModel

interface FoodRecommendCardButtonClickListener {
    fun onDetailClick(url: String, appBarTitle: String)
    fun onMapClick(foodRecommend: FoodRecommendUiModel)
}
