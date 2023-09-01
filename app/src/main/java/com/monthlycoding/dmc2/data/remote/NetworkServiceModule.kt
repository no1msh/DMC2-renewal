package com.monthlycoding.dmc2.data.remote

import com.monthlycoding.dmc2.data.remote.api.FoodRecommendService

object NetworkServiceModule {
    val foodRecommendService by lazy { NetworkModule.create<FoodRecommendService>() }
}
