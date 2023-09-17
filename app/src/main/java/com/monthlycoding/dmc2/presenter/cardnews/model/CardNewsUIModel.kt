package com.monthlycoding.dmc2.presenter.cardnews.model

data class CardNewsUIModel(
    val cardNewsId: Int,
    val content: String,
    val createdAt: String,
    val imageUrl: String,
    val title: String,
    val isNeedReadMore: Boolean = false,
    val isReadMoreCollapse: Boolean = true,
) {

    fun isVisibleReadMore(): Boolean = isNeedReadMore && isReadMoreCollapse

    companion object {
        const val MAX_CONTENT_LENGTH = 200
    }
}
