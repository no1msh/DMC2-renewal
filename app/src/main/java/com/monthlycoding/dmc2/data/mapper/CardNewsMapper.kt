package com.monthlycoding.dmc2.data.mapper

import com.monthlycoding.dmc2.data.remote.response.CardNewsDto
import com.monthlycoding.dmc2.presenter.cardnews.model.CardNewsUIModel
import com.monthlycoding.domain.model.CardNews

fun CardNewsDto.toDomain(): CardNews = CardNews(
    cardNewsId = cardNewsId,
    content = content,
    createdAt = createdAt,
    imageUrl = imageUrl,
    title = title,
)

fun CardNews.toUIModel(): CardNewsUIModel = CardNewsUIModel(
    cardNewsId = cardNewsId,
    content = content,
    createdAt = createdAt,
    imageUrl = imageUrl,
    title = title,
    isReadMoreCollapse = true,
    isNeedReadMore = content.length > CardNewsUIModel.MAX_CONTENT_LENGTH,
)
