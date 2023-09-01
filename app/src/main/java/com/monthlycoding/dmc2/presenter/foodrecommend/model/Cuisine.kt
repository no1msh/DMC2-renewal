package com.monthlycoding.dmc2.presenter.foodrecommend.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.monthlycoding.dmc2.R

enum class Cuisine(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val id: Int,
) {
    KOREAN(R.string.cuisine_title_id0, R.drawable.ic_cuisine_korean, 0),
    CHINESE(R.string.cuisine_title_id1, R.drawable.ic_cuisine_chinese, 1),
    BUNSIK(R.string.cuisine_title_id2, R.drawable.ic_cuisine_bunsik, 2),
    WESTERN(R.string.cuisine_title_id3, R.drawable.ic_cuisine_western, 3),
    JAPANESE(R.string.cuisine_title_id4, R.drawable.ic_cuisine_japanese, 4),
    FAST_FOOD(R.string.cuisine_title_id5, R.drawable.ic_cuisine_fastfood, 5),
    MEAT(R.string.cuisine_title_id6, R.drawable.ic_cuisine_meat, 6),
    CHICKEN(R.string.cuisine_title_id7, R.drawable.ic_cuisine_chicken, 7),
    CAFE(R.string.cuisine_title_id8, R.drawable.ic_cuisine_cafe, 8),
    MART(R.string.cuisine_title_id9, R.drawable.ic_cuisine_mart, 9),
    BAR(R.string.cuisine_title_id10, R.drawable.ic_cuisine_bar, 10),
    ;

    companion object {
        fun findById(id: Int): Cuisine = when (id) {
            0 -> KOREAN
            1 -> CHINESE
            2 -> BUNSIK
            3 -> WESTERN
            4 -> JAPANESE
            5 -> FAST_FOOD
            6 -> MEAT
            7 -> CHICKEN
            8 -> CAFE
            9 -> MART
            10 -> BAR
            else -> throw IllegalArgumentException("[잘못된 값: $id] 유효하지 않은 id 값입니다.")
        }
    }
}
