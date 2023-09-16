package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.data.remote.api.CardNewsService
import com.monthlycoding.dmc2.data.remote.response.CardNewsDto

class CardNewsDataSourceImpl(
    private val cardNewsService: CardNewsService
) : CardNewsDataSource {
    override suspend fun getAllCardNews(): List<CardNewsDto> {
        return cardNewsService.getAllCardNews()
    }
}
