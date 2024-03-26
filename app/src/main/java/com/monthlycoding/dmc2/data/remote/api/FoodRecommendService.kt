package com.monthlycoding.dmc2.data.remote.api

import com.monthlycoding.dmc2.data.remote.response.FoodRecommendDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecommendService {

    @GET("/category")
    suspend fun getFoodRecommends(
        @Query("category") categories: List<Int>
    ): Result<List<FoodRecommendDto>>
}
