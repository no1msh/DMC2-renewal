package com.monthlycoding.dmc2.presenter.foodrecommend

import android.os.Bundle
import android.util.TypedValue
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.setMargins
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingActivity
import com.monthlycoding.dmc2.custom.CuisineView
import com.monthlycoding.dmc2.databinding.ActivityFoodRecommendBinding
import com.monthlycoding.dmc2.presenter.foodrecommend.model.Cuisine
import com.monthlycoding.dmc2.presenter.foodrecommendcards.FoodRecommendCardsActivity

class FoodRecommendActivity :
    BindingActivity<ActivityFoodRecommendBinding>(R.layout.activity_food_recommend) {

    private val foodRecommendViewModel: FoodRecommendViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        initViewModel()
        initCuisineViews()
        addTotalCuisineView()
        initMainButtonClickListener()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbFoodRecommendToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeActionContentDescription(R.string.toolbar_back_text)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun initViewModel() {
        binding.viewModel = foodRecommendViewModel
        binding.lifecycleOwner = this
    }

    private fun initCuisineViews() {
        Cuisine.values().forEach {
            val cuisineView = createStandardCuisineView()
            cuisineView.setTitle(it.title)
            cuisineView.setIcon(it.icon)
            cuisineView.setCategoryId(it.id)
            binding.glFoodRecommendCuisineList.addView(cuisineView)
        }
    }

    private fun addTotalCuisineView() {
        val totalCuisine = createStandardCuisineView()
        totalCuisine.setTitle(getString(R.string.food_recommend_total_cuisine_view_title))
        totalCuisine.setIcon(R.drawable.ic_app_bg_white)
        totalCuisine.setOnClickListener {
            totalCuisine.isSelected = !totalCuisine.isSelected
            changeTotalCuisineIconColor(totalCuisine)
            binding.glFoodRecommendCuisineList.children
                .map { it as CuisineView }
                .forEach { cuisineView ->
                    cuisineView.isSelected = totalCuisine.isSelected
                    changeCuisineViewTitleTextColor(cuisineView)
                }
            updateDoneButtonIsEnabled()
        }
        binding.glFoodRecommendCuisineList.addView(totalCuisine)
    }

    private fun changeTotalCuisineIconColor(totalCuisine: CuisineView) {
        if (totalCuisine.isSelected) {
            totalCuisine.setIcon(R.drawable.ic_app_bg_blue)
            return
        }
        totalCuisine.setIcon(R.drawable.ic_app_bg_white)
    }

    private fun initMainButtonClickListener() {
        binding.tvFoodRecommendButtonMain.setOnClickListener {
            val temp: List<CuisineView> =
                binding.glFoodRecommendCuisineList.children.map { it as CuisineView }.toList()

            startActivity(
                FoodRecommendCardsActivity.getIntent(
                    this,
                    temp.filter { it.isSelected }.map { it.categoryId }
                        .filter { it != CuisineView.TOTAL_CATEGORY_ID })
            )
        }
    }

    private fun createStandardCuisineView(): CuisineView {
        val cuisineView = CuisineView(this)
        val layoutParams = ConstraintLayout.LayoutParams(
            getDpToPixel(DEFAULT_CUISINE_VIEW_WIDTH),
            getDpToPixel(DEFAULT_CUISINE_VIEW_HEIGHT),
        ).apply { this.setMargins(getDpToPixel(DEFAULT_CUISINE_VIEW_MARGIN)) }

        cuisineView.layoutParams = layoutParams

        cuisineView.setOnClickListener {
            it.isSelected = !it.isSelected
            changeCuisineViewTitleTextColor(it as CuisineView)
            updateDoneButtonIsEnabled()
            setIsTotalSelected()
        }
        return cuisineView
    }

    private fun updateDoneButtonIsEnabled() {
        val enabledCuisineCount =
            binding.glFoodRecommendCuisineList.children.map { it as CuisineView }.toList()
                .count { it.isSelected }

        if (enabledCuisineCount > 0) {
            foodRecommendViewModel.setIsEnabledDoneButton(true)
            binding.tvFoodRecommendButtonMain.setTextColor(
                ContextCompat.getColor(this, R.color.white)
            )
            return
        }
        foodRecommendViewModel.setIsEnabledDoneButton(false)
        binding.tvFoodRecommendButtonMain.setTextColor(
            ContextCompat.getColor(this, R.color.black)
        )
    }

    private fun changeCuisineViewTitleTextColor(cuisineView: CuisineView) {
        if (cuisineView.isSelected) {
            cuisineView.title.setTextColor(ContextCompat.getColor(this, R.color.white))
            return
        }
        cuisineView.title.setTextColor(ContextCompat.getColor(this, R.color.black))
    }

    private fun setIsTotalSelected() {
        val selectedCount = binding.glFoodRecommendCuisineList.children.map { it as CuisineView }
            .filter { it.categoryId != CuisineView.TOTAL_CATEGORY_ID && it.isSelected }.count()
        val totalCount = binding.glFoodRecommendCuisineList.childCount - TOTAL_BUTTON

        binding.glFoodRecommendCuisineList.children.map { it as CuisineView }
            .find { it.categoryId == CuisineView.TOTAL_CATEGORY_ID }?.apply {
                isSelected = selectedCount == totalCount
                changeCuisineViewTitleTextColor(this)
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getDpToPixel(dp: Int) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(), this.resources.displayMetrics
    ).toInt()

    companion object {
        private const val TOTAL_BUTTON = 1
        private const val DEFAULT_CUISINE_VIEW_WIDTH = 100
        private const val DEFAULT_CUISINE_VIEW_HEIGHT = 100
        private const val DEFAULT_CUISINE_VIEW_MARGIN = 10
    }
}
