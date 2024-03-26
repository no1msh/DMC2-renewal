package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.data.remote.api.CardNewsService
import com.monthlycoding.dmc2.data.remote.response.CardNewsDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkCardNewsDataSource @Inject constructor(
    private val cardNewsService: CardNewsService
) : CardNewsDataSource {
    override suspend fun getAllCardNews(): Result<List<CardNewsDto>> {
        return cardNewsService.getAllCardNews()
    }
}
