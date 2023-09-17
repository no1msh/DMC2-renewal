package com.monthlycoding.dmc2.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardNewsDto(
    @SerialName("cardNewsId")
    val cardNewsId: Int,
    @SerialName("content")
    val content: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("title")
    val title: String
)
