package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.data.remote.api.FoodRecommendService
import com.monthlycoding.dmc2.data.remote.response.FoodRecommendDto

class FoodRecommendDataSourceImpl(
    private val foodRecommendService: FoodRecommendService
) : FoodRecommendDataSource {
    override suspend fun getFoodRecommends(categoryIds: List<Int>): List<FoodRecommendDto> {
        return foodRecommendService.getFoodRecommends(categoryIds)
    }
}
