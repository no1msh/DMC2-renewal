package com.monthlycoding.dmc2.data.repository

import com.monthlycoding.dmc2.data.datasource.remote.FoodRecommendDataSource
import com.monthlycoding.dmc2.data.mapper.toDomain
import com.monthlycoding.dmc2.data.mapper.toResult
import com.monthlycoding.dmc2.data.remote.response.FoodRecommendDto
import com.monthlycoding.domain.model.FoodRecommend
import com.monthlycoding.domain.repository.FoodRecommendRepository
import javax.inject.Inject

class DefaultFoodRecommendRepository @Inject constructor(
    private val foodRecommendDataSource: FoodRecommendDataSource
) : FoodRecommendRepository {
    override suspend fun getFoodRecommends(categoryIds: List<Int>): Result<List<FoodRecommend>> {
        return foodRecommendDataSource.getFoodRecommends(categoryIds).toResult {
            it.map(FoodRecommendDto::toDomain)
        }
    }
}
