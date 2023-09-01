package com.monthlycoding.dmc2.common

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("isEnabled")
    fun isEnabled(view: View, isEnabled: Boolean) {
        view.isEnabled = isEnabled
    }

    @JvmStatic
    @BindingAdapter("setDrawableResource")
    fun setDrawableResource(imageView: ImageView, @DrawableRes id: Int) {
        imageView.setImageResource(id)
    }
}
