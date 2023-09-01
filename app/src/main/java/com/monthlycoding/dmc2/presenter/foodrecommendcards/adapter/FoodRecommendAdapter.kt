package com.monthlycoding.dmc2.presenter.foodrecommendcards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.monthlycoding.dmc2.presenter.foodrecommendcards.FoodRecommendCardButtonClickListener
import com.monthlycoding.dmc2.presenter.foodrecommendcards.adapter.viewHolder.FoodRecommendViewHolder
import com.monthlycoding.dmc2.presenter.foodrecommendcards.model.FoodRecommendUiModel

class FoodRecommendAdapter(
    private val onCardButtonClickListener: FoodRecommendCardButtonClickListener,
) : ListAdapter<FoodRecommendUiModel, FoodRecommendViewHolder>(diffCallback) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).storeId.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodRecommendViewHolder {
        return FoodRecommendViewHolder(
            FoodRecommendViewHolder.getView(
                parent,
                LayoutInflater.from(parent.context)
            ),
            onCardButtonClickListener,
        )
    }

    override fun onBindViewHolder(holder: FoodRecommendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<FoodRecommendUiModel>() {
            override fun areItemsTheSame(
                oldItem: FoodRecommendUiModel,
                newItem: FoodRecommendUiModel
            ): Boolean = oldItem.storeId == newItem.storeId

            override fun areContentsTheSame(
                oldItem: FoodRecommendUiModel,
                newItem: FoodRecommendUiModel
            ): Boolean = oldItem == newItem
        }
    }
}
