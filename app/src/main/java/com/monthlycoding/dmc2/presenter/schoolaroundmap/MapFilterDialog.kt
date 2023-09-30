package com.monthlycoding.dmc2.presenter.schoolaroundmap

import android.app.Dialog
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.setMargins
import androidx.databinding.DataBindingUtil
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.custom.CuisineView
import com.monthlycoding.dmc2.databinding.DialogMapFilterBinding
import com.monthlycoding.dmc2.presenter.foodrecommend.model.Cuisine

class MapFilterDialog(
    context: Context,
    categorySelectDone: MapFilterDialogClickListener
) : Dialog(context) {

    private val binding: DialogMapFilterBinding by lazy {
        DataBindingUtil
            .inflate(LayoutInflater.from(context), R.layout.dialog_map_filter, null, false)
    }

    init {
        setContentView(binding.root)
        initCuisineViews()
        addTotalCuisineView()

        binding.tvMapFilterButtonDone.setOnClickListener {
            dismiss()
            val temp: List<CuisineView> =
                binding.glMapFilterCuisineList.children.map { it as CuisineView }.toList()
            val ids: List<Int> = temp
                .filter { it.isSelected }
                .map { it.categoryId }
                .filter { it != CuisineView.TOTAL_CATEGORY_ID }
            categorySelectDone.onCategorySelectDoneClick(ids)
        }

    }

    private fun initCuisineViews() {
        Cuisine.values().forEach {
            val cuisineView = createStandardCuisineView()
            cuisineView.setTitle(it.title)
            cuisineView.setIcon(it.icon)
            cuisineView.setCategoryId(it.id)
            binding.glMapFilterCuisineList.addView(cuisineView)
        }
    }

    private fun addTotalCuisineView() {
        val totalCuisine = createStandardCuisineView()
        totalCuisine.setTitle(context.getString(R.string.food_recommend_total_cuisine_view_title))
        totalCuisine.setIcon(R.drawable.ic_app_bg_white)
        totalCuisine.setOnClickListener {
            totalCuisine.isSelected = !totalCuisine.isSelected
            changeTotalCuisineIcon(totalCuisine)
            binding.glMapFilterCuisineList.children
                .map { it as CuisineView }
                .forEach { cuisineView ->
                    cuisineView.isSelected = totalCuisine.isSelected
                    changeCuisineViewTitleTextColor(cuisineView)
                }
        }
        binding.glMapFilterCuisineList.addView(totalCuisine)
    }

    private fun changeTotalCuisineIcon(totalCuisine: CuisineView) {
        if (totalCuisine.isSelected) {
            totalCuisine.setIcon(R.drawable.ic_app_bg_blue)
            return
        }
        totalCuisine.setIcon(R.drawable.ic_app_bg_white)
    }

    private fun createStandardCuisineView(): CuisineView {
        val cuisineView = CuisineView(context)
        val defaultSize = context.resources.displayMetrics.widthPixels / 4
        val layoutParams = ConstraintLayout.LayoutParams(defaultSize, defaultSize)
            .apply { this.setMargins(getDpToPixel(DEFAULT_CUISINE_VIEW_MARGIN)) }

        cuisineView.layoutParams = layoutParams

        cuisineView.setOnClickListener {
            it.isSelected = !it.isSelected
            changeCuisineViewTitleTextColor(it as CuisineView)
        }
        return cuisineView
    }

    private fun changeCuisineViewTitleTextColor(cuisineView: CuisineView) {
        if (cuisineView.isSelected) {
            cuisineView.title.setTextColor(ContextCompat.getColor(context, R.color.white))
            return
        }
        cuisineView.title.setTextColor(ContextCompat.getColor(context, R.color.black))
    }

    private fun getDpToPixel(dp: Int) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(), context.resources.displayMetrics
    ).toInt()

    companion object {
        private const val DEFAULT_CUISINE_VIEW_MARGIN = 8
    }
}
