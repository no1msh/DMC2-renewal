package com.monthlycoding.dmc2.presenter.foodrecommendcards

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.data.mapper.toCuisineMarker
import com.monthlycoding.dmc2.databinding.ActivityFoodRecommendCardsBinding
import com.monthlycoding.dmc2.presenter.foodRecommendDetail.FoodRecommendDetailWebActivity
import com.monthlycoding.dmc2.presenter.foodrecommendcards.adapter.FoodRecommendAdapter
import com.monthlycoding.dmc2.presenter.foodrecommendcards.model.FoodRecommendUiModel
import com.monthlycoding.dmc2.presenter.schoolaroundmap.SchoolAroundMapActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodRecommendCardsActivity :
    BindingActivity<ActivityFoodRecommendCardsBinding>(R.layout.activity_food_recommend_cards),
    FoodRecommendCardButtonClickListener {

    private val foodRecommendCardsViewModel: FoodRecommendCardsViewModel by viewModels()
    private val foodRecommendAdapter: FoodRecommendAdapter by lazy { FoodRecommendAdapter(this) }
    private val viewPagerCustomUtil: ViewPagerCustomUtil by lazy {
        ViewPagerCustomUtil(
            binding.vpFoodRecommendCards,
            this
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindViewModel()
        initActionBar()
        initAdapter()
        val categoryIds: IntArray = intent.getIntArrayExtra(KEY_CATEGORY_IDS) ?: IntArray(0)
        foodRecommendCardsViewModel.fetchFoodRecommends(categoryIds.toList())
        observeFoodRecommends()
    }

    private fun bindViewModel() {
        binding.viewModel = foodRecommendCardsViewModel
        binding.lifecycleOwner = this
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbFoodRecommendCardsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun observeFoodRecommends() {
        foodRecommendCardsViewModel.foodRecommends.observe(this) { foodRecommends ->
            foodRecommendAdapter.submitList(foodRecommends)
        }
    }

    private fun initAdapter() {
        viewPagerCustomUtil.setCustomViewPager()
        binding.vpFoodRecommendCards.adapter = foodRecommendAdapter
    }

    override fun onDetailClick(url: String, appBarTitle: String) {
        startActivity(FoodRecommendDetailWebActivity.getIntent(this, url, appBarTitle))
    }

    override fun onMapClick(foodRecommend: FoodRecommendUiModel) {
        startActivity(SchoolAroundMapActivity.getIntent(this, foodRecommend.toCuisineMarker()))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
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
