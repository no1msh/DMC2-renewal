package com.monthlycoding.dmc2.data.datasource.remote

import com.monthlycoding.dmc2.common.CustomResult
import com.monthlycoding.dmc2.data.remote.response.CardNewsDto

interface CardNewsDataSource {
    suspend fun getAllCardNews(): CustomResult<List<CardNewsDto>>
}
