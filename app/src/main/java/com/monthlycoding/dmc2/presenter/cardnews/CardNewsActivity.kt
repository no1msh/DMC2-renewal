package com.monthlycoding.dmc2.presenter.cardnews

import android.os.Bundle
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.databinding.ActivityCardNewsBinding
import com.monthlycoding.dmc2.presenter.cardnews.adapter.CardNewsAdapter

class CardNewsActivity : BindingActivity<ActivityCardNewsBinding>(R.layout.activity_card_news) {

    private val cardNewsViewModel: CardNewsViewModel by viewModels { CardNewsViewModel.Factory }
    private val cardNewsAdapter: CardNewsAdapter by lazy {
        CardNewsAdapter(
            cardNewsViewModel,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        initCardNewsAdapter()
        cardNewsViewModel.getAllCardNews()
        observeAllCardNews()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbCardNewsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initCardNewsAdapter() {
        binding.rvCardNewsList.adapter = cardNewsAdapter
    }

    private fun observeAllCardNews() {
        cardNewsViewModel.allCardNews.observe(this) { allCardNews ->
            cardNewsAdapter.submitList(allCardNews)
        }
    }
}
