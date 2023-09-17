package com.monthlycoding.domain.repository

import com.monthlycoding.domain.model.CardNews

interface CardNewsRepository {
    suspend fun getAllCardNews(): List<CardNews>
}
