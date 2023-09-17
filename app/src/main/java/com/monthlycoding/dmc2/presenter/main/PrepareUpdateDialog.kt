package com.monthlycoding.dmc2.presenter.main

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import com.monthlycoding.dmc2.R
import com.monthlycoding.dmc2.common.BindingDialogFragment
import com.monthlycoding.dmc2.databinding.DialogPrepareForUpdateBinding

class PrepareUpdateDialog :
    BindingDialogFragment<DialogPrepareForUpdateBinding>(R.layout.dialog_prepare_for_update) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.tvDialogPrepareForUpdateCancel.setOnClickListener { dismiss() }
        super.onViewCreated(view, savedInstanceState)
    }
}
