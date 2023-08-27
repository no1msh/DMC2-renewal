package com.monthlycoding.dmc2.common

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("isEnabled")
    fun isEnabled(view: View, isEnabled: Boolean) {
        view.isEnabled = isEnabled
    }
}
