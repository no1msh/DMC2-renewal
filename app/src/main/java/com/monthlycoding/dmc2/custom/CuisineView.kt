package com.monthlycoding.dmc2.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.databinding.ViewCuisineBinding

class CuisineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ViewCuisineBinding by lazy {
        ViewCuisineBinding.inflate(LayoutInflater.from(context), this, true)
    }
    val title: TextView by lazy { binding.tvCuisineTitle }
    private val icon: ImageView by lazy { binding.ivCuisineIcon }
    var categoryId: Int = TOTAL_CATEGORY_ID
        private set

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CuisineView, 0, 0).apply {
            runCatching {
                title.text = getString(R.styleable.CuisineView_cuisineTitle)
                icon.setImageResource(
                    getResourceId(R.styleable.CuisineView_cuisineIcon, 0),
                )
            }.also { recycle() }
        }
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setTitle(@StringRes id: Int) {
        title.setText(id)
    }

    fun setIcon(@DrawableRes iconRes: Int) {
        icon.setImageResource(iconRes)
    }

    fun setCategoryId(id: Int) {
        categoryId = id
    }

    companion object {
        const val TOTAL_CATEGORY_ID = Int.MIN_VALUE
    }
}
