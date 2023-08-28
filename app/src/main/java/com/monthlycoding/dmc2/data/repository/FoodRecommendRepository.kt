package com.monthlycoding.dmc2.data.repository

import com.monthlycoding.dmc2.data.datasource.remote.FoodRecommendDataSource
import com.monthlycoding.dmc2.data.mapper.toDomain
import com.monthlycoding.domain.model.FoodRecommend
import com.monthlycoding.domain.repository.FoodRecommendRepository

class FoodRecommendRepository(
    private val foodRecommendDataSource: FoodRecommendDataSource
) : FoodRecommendRepository {
    override suspend fun getFoodRecommends(categoryIds: List<Int>): List<FoodRecommend> {
        return foodRecommendDataSource.getFoodRecommends(categoryIds).map { it.toDomain() }
    }
}
