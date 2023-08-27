package com.monthlycoding.dmc2.presenter.foodrecommendcards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivityFoodRecommendCardsBinding

class FoodRecommendCardsActivity :
    BindingActivity<ActivityFoodRecommendCardsBinding>(R.layout.activity_food_recommend_cards) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvTemp.text = intent.getIntArrayExtra(KEY_CATEGORY_IDS)?.toList().toString()
    }

    companion object {
        private const val KEY_CATEGORY_IDS = "KEY_CATEGORY_IDS"

        fun getIntent(context: Context, ids: List<Int>): Intent {
            val intent = Intent(context, FoodRecommendCardsActivity::class.java).apply {
                this.putExtra(KEY_CATEGORY_IDS, ids.toIntArray())
            }
            return intent
        }
    }
}
