package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.common.CustomResult
import com.monthlycoding.dmc2.data.remote.api.FoodRecommendService
import com.monthlycoding.dmc2.data.remote.response.FoodRecommendDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkFoodRecommendDataSource @Inject constructor(
    private val foodRecommendService: FoodRecommendService
) : FoodRecommendDataSource {
    override suspend fun getFoodRecommends(categoryIds: List<Int>): CustomResult<List<FoodRecommendDto>> {
        return foodRecommendService.getFoodRecommends(categoryIds)
    }
}
