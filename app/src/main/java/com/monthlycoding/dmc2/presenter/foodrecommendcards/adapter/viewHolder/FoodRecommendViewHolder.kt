package com.monthlycoding.dmc2.presenter.foodrecommendcards.adapter.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.monthlycoding.dmc2.databinding.ItemFoodRecommendCardBinding
import com.monthlycoding.dmc2.presenter.foodrecommend.model.Cuisine
import com.monthlycoding.dmc2.presenter.foodrecommendcards.FoodRecommendCardButtonClickListener
import com.monthlycoding.dmc2.presenter.foodrecommendcards.model.FoodRecommendUiModel

class FoodRecommendViewHolder(
    private val binding: ItemFoodRecommendCardBinding,
    private val onCardButtonClickListener: FoodRecommendCardButtonClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(foodRecommend: FoodRecommendUiModel) {
        binding.foodRecommend = foodRecommend
        binding.cuisine = Cuisine.findById(foodRecommend.categoryId)
        binding.onCardButtonClickListener = onCardButtonClickListener
    }

    companion object {
        fun getView(
            parent: ViewGroup,
            layoutInflater: LayoutInflater
        ): ItemFoodRecommendCardBinding =
            ItemFoodRecommendCardBinding.inflate(layoutInflater, parent, false)
    }
}
