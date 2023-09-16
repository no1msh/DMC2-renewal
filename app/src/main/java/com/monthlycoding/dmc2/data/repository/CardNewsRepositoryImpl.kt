package com.monthlycoding.dmc2.data.repository

import com.monthlycoding.dmc2.data.datasource.remote.CardNewsDataSource
import com.monthlycoding.dmc2.data.mapper.toDomain
import com.monthlycoding.domain.model.CardNews
import com.monthlycoding.domain.repository.CardNewsRepository

class CardNewsRepositoryImpl(
    private val cardNewsDataSource: CardNewsDataSource
) : CardNewsRepository {
    override suspend fun getAllCardNews(): List<CardNews> {
        return cardNewsDataSource.getAllCardNews().map { it.toDomain() }
    }
}
