package com.monthlycoding.domain.repository

import com.monthlycoding.domain.model.FoodRecommend

interface FoodRecommendRepository {
    suspend fun getFoodRecommends(categoryIds: List<Int>): List<FoodRecommend>
}
