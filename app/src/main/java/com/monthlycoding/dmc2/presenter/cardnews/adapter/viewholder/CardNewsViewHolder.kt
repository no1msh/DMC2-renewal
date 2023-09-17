package com.monthlycoding.dmc2.presenter.cardnews.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.monthlycoding.dmc2.databinding.ItemCardNewsBinding
import com.monthlycoding.dmc2.presenter.cardnews.CardNewsViewModel
import com.monthlycoding.dmc2.presenter.cardnews.model.CardNewsUIModel

class CardNewsViewHolder(
    private val binding: ItemCardNewsBinding,
    cardNewsViewModel: CardNewsViewModel,
    lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.cardNewsViewModel = cardNewsViewModel
        binding.lifecycleOwner = lifecycleOwner
    }

    fun bind(item: CardNewsUIModel) {
        binding.cardNews = item
    }

    companion object {
        fun getBinding(parent: ViewGroup): ItemCardNewsBinding {
            val inflater = LayoutInflater.from(parent.context)
            return ItemCardNewsBinding.inflate(inflater, parent, false)
        }
    }
}
