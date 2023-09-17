package com.monthlycoding.dmc2.presenter.cardnews.adapter

import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.monthlycoding.dmc2.presenter.cardnews.CardNewsViewModel
import com.monthlycoding.dmc2.presenter.cardnews.adapter.viewholder.CardNewsViewHolder
import com.monthlycoding.dmc2.presenter.cardnews.model.CardNewsUIModel

class CardNewsAdapter(
    private val cardNewsViewModel: CardNewsViewModel,
    private val lifecycleOwner: LifecycleOwner
) :
    ListAdapter<CardNewsUIModel, CardNewsViewHolder>(diffCallBack) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).cardNewsId.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardNewsViewHolder {
        return CardNewsViewHolder(
            CardNewsViewHolder.getBinding(parent),
            cardNewsViewModel,
            lifecycleOwner,
        )
    }

    override fun onBindViewHolder(holder: CardNewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<CardNewsUIModel>() {
            override fun areItemsTheSame(
                oldItem: CardNewsUIModel,
                newItem: CardNewsUIModel
            ): Boolean =
                oldItem.cardNewsId == newItem.cardNewsId

            override fun areContentsTheSame(
                oldItem: CardNewsUIModel,
                newItem: CardNewsUIModel
            ): Boolean =
                oldItem == newItem

        }
    }
}
