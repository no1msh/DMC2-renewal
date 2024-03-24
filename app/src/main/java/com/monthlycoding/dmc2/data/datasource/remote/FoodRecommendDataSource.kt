package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.common.CustomResult
import com.monthlycoding.dmc2.data.remote.response.FoodRecommendDto

interface FoodRecommendDataSource {
    suspend fun getFoodRecommends(categoryIds: List<Int>): CustomResult<List<FoodRecommendDto>>
}
