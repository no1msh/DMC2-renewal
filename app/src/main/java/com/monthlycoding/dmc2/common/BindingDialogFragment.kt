package com.monthlycoding.dmc2.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.monthlycoding.dmc2.R

abstract class BindingDialogFragment<B : ViewDataBinding>(
    @LayoutRes private val layoutResId: Int,
) : DialogFragment() {
    private var _binding: B? = null
    val binding get() = _binding ?: error(R.string.error_baseFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
