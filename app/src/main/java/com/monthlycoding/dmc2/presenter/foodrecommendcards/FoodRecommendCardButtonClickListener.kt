package com.monthlycoding.dmc2.presenter.foodrecommendcards

interface FoodRecommendCardButtonClickListener {
    fun onDetailClick(url: String, appBarTitle: String)
    fun onMapClick()
}
