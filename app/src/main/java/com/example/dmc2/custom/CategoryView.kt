package com.example.dmc2.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dmc2.R
import com.example.dmc2.databinding.ItemMainListBinding

class CategoryView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: ItemMainListBinding by lazy {
        ItemMainListBinding.inflate(LayoutInflater.from(context), this, true)
    }
    private val title: TextView by lazy { binding.tvItemMainTitle }
    private val description: TextView by lazy { binding.tvItemMainDescription }
    private val icon: ImageView by lazy { binding.ivItemMainIcon }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CategoryView, 0, 0).apply {
            runCatching {
                title.text = getString(R.styleable.CategoryView_categoryTitle)
                description.text = getString(R.styleable.CategoryView_categoryDescription)
                icon.setImageResource(
                    getResourceId(R.styleable.CategoryView_categoryIcon, 0),
                )
            }.also { recycle() }
        }
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setDescription(text: String) {
        description.text = text
    }

    fun setIcon(@DrawableRes iconRes: Int) {
        icon.setImageResource(iconRes)
    }
}
