package com.monthlycoding.dmc2.data.remote.api

import com.monthlycoding.dmc2.data.remote.response.CardNewsDto
import retrofit2.http.GET

interface CardNewsService {

    @GET("/cardnews")
    suspend fun getAllCardNews(): List<CardNewsDto>
}
